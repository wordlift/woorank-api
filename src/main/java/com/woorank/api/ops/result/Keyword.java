package com.woorank.api.ops.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.woorank.api.ops.GetKeywordsDataOperation;
import lombok.Getter;

import java.util.ArrayList;

/**
 * Operation Results: Keyword
 * <p>
 * Represents an {@link GetKeywordsDataOperation}'s result.
 *
 * @since 1.0.0
 */
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Keyword {

    private String id;
    private String keyword;
    private String country;
    private String language;
    private LatestResult latestResult;
    private LatestVolume latestVolume;
    private ArrayList<KeywordUrl> urls;

}
