package org.glenlivet.dict.domain.wordsapi;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by glenlivet on 9/30/16.
 */
public class WordJsonFactory {

    public static JsonNode createWordJson(WordResponse wordResponse) throws JsonProcessingException {
        final AtomicInteger id = new AtomicInteger(1);
        final JsonNodeFactory factory = new JsonNodeFactory(true);
        ObjectNode rootNode = factory.objectNode();
        //data
        ObjectNode data = factory.objectNode();
        data.put("type", "words");
        data.put("id", "" + id.getAndIncrement());

        //attributes
        ObjectNode attributes = factory.objectNode();
        attributes.put("spell", wordResponse.getWord());
        attributes.put("pronunciation", wordResponse.getPronunciation().getAll());
        attributes.put("frequency", wordResponse.getFrequency());
        data.set("attributes", attributes);

        //included
        List<Definition> defList = wordResponse.getResults();
        List<ObjectNode> defObjList = defList.stream().map(def -> {
            ObjectNode defObject = factory.objectNode();
            defObject.put("type", "definitions");
            defObject.put("id", "" + id.getAndIncrement());
            //attributes
            ObjectNode attrs = factory.objectNode();
            attrs.put("description", def.getDefinition());
            attrs.put("part-of-speech", def.getPartOfSpeech());
            ArrayNode synonyms = attrs.putArray("synonyms");
            if(def.getSynonyms() != null) {
                def.getSynonyms()
                        .stream()
                        .forEach(syn -> {
                            synonyms.add(syn);
                        });
            }
            ArrayNode examples = attrs.putArray("examples");
            if(def.getExamples() != null) {
                def.getExamples()
                        .stream()
                        .forEach(example -> {
                            examples.add(example);
                        });
            }
            defObject.set("attributes", attrs);
            return defObject;
        }).collect(Collectors.toList());

        //relationships
        ObjectNode relationships = data.putObject("relationships");
        ObjectNode defInRelationships = relationships.putObject("definitions");
        ArrayNode dataInDef = defInRelationships.putArray("data");

        defObjList.stream()
                .map(defObj -> {
                    ObjectNode d = factory.objectNode();
                    d.set("type", defObj.get("type"));
                    d.set("id", defObj.get("id"));
                    return d;
                }).forEach(d -> {
            dataInDef.add(d);
        });

        //construct the root object
        rootNode.set("data", data);
        rootNode.putArray("included").addAll(defObjList);

        return rootNode;
    }
}
