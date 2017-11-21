package com.woorank.api.ops.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.woorank.api.ops.GetKeywordsDataOperation;
import lombok.Getter;

import java.time.Instant;

/**
 * Operation Results: Keyword Url Result
 * <p>
 * Represents an {@link GetKeywordsDataOperation}'s result.
 *
 * @since 1.0.0
 */
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
class KeywordUrlResult {

    private String id;

    @JsonProperty("scraped_at")
    private Instant scrapedAt;

    private int rank;

    @JsonProperty("found_url")
    private String foundUrl;

}
