package com.woorank.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.util.ArrayList;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
class KeywordUrl {

    private String id;
    private String url;
    private int twoWeekRank;
    private int prevWeekRank;
    private boolean hasEnteredTopHundred;
    private boolean hasLeftTopHundred;
    private int rankDiff;
    private int rank;
    private ArrayList<KeywordUrlResult> results;

}
