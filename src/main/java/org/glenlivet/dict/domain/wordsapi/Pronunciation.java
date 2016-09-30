package org.glenlivet.dict.domain.wordsapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by glenlivet on 9/29/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pronunciation {

    private String all;

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }
}
