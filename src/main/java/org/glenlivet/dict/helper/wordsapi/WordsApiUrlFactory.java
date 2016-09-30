package org.glenlivet.dict.helper.wordsapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by glenlivet on 9/29/16.
 */
@Component
public class WordsApiUrlFactory {

    @Value("${wordsapi.url}")
    private String rootUrl;

    public String getUrl(String type, String word) {
        switch(type) {
            case "word":
                return rootUrl + "/" + word;
            default:
                return rootUrl + "/" + word;
        }
    }

    public String getUrl(String word) {
        return getUrl("word", word);
    }

}
