package org.glenlivet.dict.helper.wordsapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.glenlivet.dict.domain.wordsapi.Definition;
import org.glenlivet.dict.domain.wordsapi.Pronunciation;
import org.glenlivet.dict.domain.wordsapi.WordJsonFactory;
import org.glenlivet.dict.domain.wordsapi.WordResponse;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Created by glenlivet on 9/30/16.
 */
public class WordsApiUrlFactoryTest {

    private WordResponse wordResponse;

    @Before
    public void init() {

        Pronunciation pron = new Pronunciation();
        pron.setAll("ɪɡ'zæmpəl");

        Definition def1 = new Definition();
        def1.setDefinition("a representative form or pattern");
        def1.setExamples(Lists.newArrayList("I profited from his example."));
        def1.setPartOfSpeech("noun");
        def1.setSynonyms(Lists.newArrayList("model"));

        Definition def2 = new Definition();
        def2.setDefinition("something to be imitated");
        def2.setPartOfSpeech("noun");
        def2.setSynonyms(Lists.newArrayList("exemplar", "good example", "model"));

        List<Definition> defList = Lists.newArrayList(def1, def2);

        wordResponse = new WordResponse();
        wordResponse.setFrequency(new BigDecimal(4.67).setScale(2, RoundingMode.HALF_UP));
        wordResponse.setPronunciation(pron);
        wordResponse.setWord("example");
        wordResponse.setResults(defList);



    }

    @Test
    public void testCreateWordJson() throws JsonProcessingException {
        JsonNode json = WordJsonFactory.createWordJson(wordResponse);

        ObjectMapper objectMapper = new ObjectMapper();
        String str = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);

        System.out.println(str);

    }
}
