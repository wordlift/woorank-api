package com.woorank.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.Instant;


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
