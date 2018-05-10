package com.woorank.api.ops.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import java.net.URL;
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
        private int twoWeekRank;

        @Getter
        private int prevWeekRank;

        @Getter
        private boolean hasEnteredTopHundred;

        @Getter
        private boolean hasLeftTopHundred;

        @Getter
        private int rankDiff;

        @Getter
        private int rank;

        @Getter
        private Result[] results;

    }

    @JsonIgnoreProperties({"id", "scraped_at", "found_url"})
    public static class Result {

        @Getter
        private Instant scrapedAt;

        @Getter
        private int rank;

        @Getter
        private URL foundUrl;

    }

    @JsonIgnoreProperties({"id", "keyword_id", "keywordId", "scraped_at"})
    public static class LatestResult {

        @Getter
        private Instant scrapedAt;

    }

    @JsonIgnoreProperties({"id", "keyword_id", "keywordId"})
    public static class LatestVolume {

        @Getter
        private int volume;

    }

}
