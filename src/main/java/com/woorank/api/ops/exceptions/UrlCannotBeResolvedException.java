package com.woorank.api.ops.exceptions;

import org.apache.http.HttpResponse;

public class UrlCannotBeResolvedException extends AbstractErrorException {

    private final String url;

    public UrlCannotBeResolvedException(HttpResponse response, String url) {
        super(response);

        this.url = url;
    }

}
