package com.woorank.api.ops.result;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Error {

    public static Error UNKNOWN_ERROR = new Error(0, "Unknown Error");

    private final int status;

    @NonNull
    private final String message;

}
