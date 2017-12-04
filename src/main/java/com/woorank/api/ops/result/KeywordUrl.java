package com.woorank.api.ops.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.woorank.api.ops.GetKeywordsDataOperation;
import lombok.Getter;

import java.util.ArrayList;

/**
 * Operation Results: Keyword Url
 * <p>
 * Represents an {@link GetKeywordsDataOperation}'s result.
 *
 * @since 1.0.0
 */
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class KeywordUrl {

    private String id;
    private String url;

    // The following 2 are `Integer` to allow null values.
    private Integer twoWeekRank;
    private Integer prevWeekRank;

    private boolean hasEnteredTopHundred;
    private boolean hasLeftTopHundred;
    private int rankDiff;
    private int rank;
    private ArrayList<KeywordUrlResult> results;

}
