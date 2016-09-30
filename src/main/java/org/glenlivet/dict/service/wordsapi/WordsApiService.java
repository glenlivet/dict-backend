package org.glenlivet.dict.service.wordsapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.glenlivet.dict.Application;
import org.glenlivet.dict.domain.wordsapi.WordJsonFactory;
import org.glenlivet.dict.domain.wordsapi.WordResponse;
import org.glenlivet.dict.helper.wordsapi.WordsApiUrlFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Created by glenlivet on 9/29/16.
 */
@Service
public class WordsApiService {

    static Logger logger = LoggerFactory.getLogger(WordsApiService.class);

    @Autowired
    private WordsApiUrlFactory wordsApiUrlFactory;

    @Resource(name=Application.BEAN_NAME_WORDS_API_HTTP_ENTITY_FACTORY)
    private ObjectFactory<HttpEntity> wordsApiHttpEntityFactory;

    @Autowired
    private RestTemplate restTemplate;

    public JsonNode getWord(String word) throws JsonProcessingException {
        ResponseEntity<WordResponse> wordResponse = restTemplate.exchange(wordsApiUrlFactory.getUrl(word), HttpMethod.GET,
                wordsApiHttpEntityFactory.getObject(), WordResponse.class);
        return WordJsonFactory.createWordJson(wordResponse.getBody());
    }

}
