package com.woorank.api.ops.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;

@RequiredArgsConstructor
public class InvalidResponseException extends Exception {

    @Getter
    private final HttpResponse response;

}
