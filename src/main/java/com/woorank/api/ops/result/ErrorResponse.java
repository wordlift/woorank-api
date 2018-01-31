package com.woorank.api.ops.result;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ErrorResponse {

    @Getter
    private final Error error;

}
