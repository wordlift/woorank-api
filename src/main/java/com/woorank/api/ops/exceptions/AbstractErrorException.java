package com.woorank.api.ops.exceptions;

import com.woorank.api.ops.result.Error;
import com.woorank.api.ops.result.ErrorResponse;
import com.woorank.api.utils.JsonUtils;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.http.HttpResponse;

abstract class AbstractErrorException extends Exception {

    @Getter
    private ErrorResponse errorResponse;

    @SneakyThrows
    AbstractErrorException(HttpResponse response) {
        try {
            this.errorResponse = JsonUtils.getReader()
                    .forType(ErrorResponse.class)
                    .readValue(response.getEntity().getContent());
        } catch (Exception e) {
            this.errorResponse = new ErrorResponse(Error.UNKNOWN_ERROR);
        }
    }

    @Override
    public String getMessage() {
        return this.errorResponse.getError().getMessage();
    }
}
