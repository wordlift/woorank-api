package com.woorank.api.ops.exceptions;

import org.apache.http.HttpResponse;

public class ProjectAlreadyExistsException extends AbstractErrorException {

    public ProjectAlreadyExistsException(HttpResponse response) {
        super(response);
    }

}
