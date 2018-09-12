package com.woorank.api.ops.result;

import lombok.Getter;

public class CreateProjectResult {

    @Getter
    private Data data;

    public static class Data {

        @Getter
        private String id;

        @Getter
        private Boolean isProject;

    }

}
