package com.woorank.api.ops.exceptions;

import org.apache.http.HttpResponse;

public class InvalidResponseException extends AbstractErrorException {

    public InvalidResponseException(HttpResponse response) {
        super(response);
    }

}
