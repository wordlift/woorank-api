package com.woorank.api.ops.result;

import lombok.Getter;

import java.net.URL;
import java.time.Instant;

public class GetKeywordsDataResult {

    @Getter
    private Data[] data;

    public static class Data {

        @Getter
        private String keyword;

        @Getter
        private String country;

        @Getter
        private String language;

        @Getter
        private Url[] urls;

    }

    public static class Url {

        @Getter
        private String url;

        @Getter
        private Result[] results;

    }

    public static class Result {

        @Getter
        private Instant scrapedAt;

        @Getter
        private int rank;

        @Getter
        private URL foundUrl;

    }

}
