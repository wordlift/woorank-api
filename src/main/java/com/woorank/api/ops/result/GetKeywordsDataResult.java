package com.woorank.api.ops.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.time.Instant;

public class GetKeywordsDataResult {

    @Getter
    private Data[] data;

    @JsonIgnoreProperties("id")
    public static class Data {

        @Getter
        private String keyword;

        @Getter
        private String country;

        @Getter
        private String language;

        @Getter
        private Url[] urls;

        @Getter
        private LatestResult latestResult;

        @Getter
        private LatestVolume latestVolume;

    }

    @JsonIgnoreProperties("id")
    public static class Url {

        @Getter
        private String url;

        @Getter
        private Integer twoWeekRank;

        @Getter
        private Integer prevWeekRank;

        @Getter
        private Boolean hasEnteredTopHundred;

        @Getter
        private Boolean hasLeftTopHundred;

        @Getter
        private Integer rankDiff;

        @Getter
        private Integer rank;

        @Getter
        private Result[] results;

    }

    @JsonIgnoreProperties({"id", "scraped_at", "found_url"})
    public static class Result {

        @Getter
        private Instant scrapedAt;

        @Getter
        private Integer rank;

        @Getter
        private String foundUrl;

    }

    @JsonIgnoreProperties({"id", "keyword_id", "keywordId", "scraped_at"})
    public static class LatestResult {

        @Getter
        private Instant scrapedAt;

    }

    @JsonIgnoreProperties({"id", "keyword_id", "keywordId"})
    public static class LatestVolume {

        @Getter
        private Integer volume;

    }

}
