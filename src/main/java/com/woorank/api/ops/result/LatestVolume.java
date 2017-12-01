package com.woorank.api.ops.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.Instant;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LatestVolume {

    private String id;

    @JsonProperty("keyword_id")
    private String keywordId;

    // The following is nullable.
    private Integer volume;

}
