package com.woorank.api.ops.exceptions;

import com.woorank.api.ops.result.Error;
import com.woorank.api.ops.result.ErrorResponse;
import com.woorank.api.utils.JsonUtils;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.http.HttpResponse;

abstract class AbstractErrorException extends Exception {

    @Getter
    private ErrorResponse error;

    @SneakyThrows
    AbstractErrorException(HttpResponse response) {
        try {
            this.error = JsonUtils.getReader()
                    .forType(ErrorResponse.class)
                    .readValue(response.getEntity().getContent());
        } catch (Exception e) {
            this.error = new ErrorResponse(Error.UNKNOWN_ERROR);
        }
    }

}
