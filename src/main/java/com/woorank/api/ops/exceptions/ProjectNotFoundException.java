package com.woorank.api.ops.exceptions;

import org.apache.http.HttpResponse;

public class ProjectNotFoundException extends AbstractErrorException {

    public ProjectNotFoundException(HttpResponse response) {
        super(response);
    }

}
