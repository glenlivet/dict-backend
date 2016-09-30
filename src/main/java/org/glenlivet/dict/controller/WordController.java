package org.glenlivet.dict.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.glenlivet.dict.service.wordsapi.WordsApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by glenlivet on 9/29/16.
 */
@RestController
@RequestMapping("/words")
public class WordController {

    @Autowired
    private WordsApiService wordsApiService;

    @RequestMapping(value = "/{word}", method= RequestMethod.GET)
    public JsonNode getWord(@PathVariable String word) throws JsonProcessingException {
        return wordsApiService.getWord(word);
    }

}
