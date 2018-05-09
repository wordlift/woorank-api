package com.woorank.api.ops.result;

import lombok.Getter;

public class CreateProjectResult {

    @Getter
    private Data data;

    public static class Data {

        @Getter
        private String id;

        @Getter
        private Review review;

    }

    public static class Review {

        @Getter
        private boolean advanced;

        @Getter
        private int lastScore;

    }

}
