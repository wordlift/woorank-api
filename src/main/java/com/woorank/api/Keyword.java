package com.woorank.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.ArrayList;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
class Keyword {

    private String id;
    private String keyword;
    private String country;
    private String language;
    private ArrayList<KeywordUrl> urls;

}
