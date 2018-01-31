package com.woorank.api.ops;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.Set;

@Getter
@Builder
public class KeywordRequest {

    @NonNull
    private final Set<String> keywords;

    private final String country;

    private final String language;

}
