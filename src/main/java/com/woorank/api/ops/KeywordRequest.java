package com.woorank.api.ops;

import com.sun.istack.internal.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
public class KeywordRequest {

    @NotNull
    private final Set<String> keywords;

    @NotNull
    private final String country;

    private final String language;

}
