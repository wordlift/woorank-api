package com.woorank.api.ops.exceptions;

import com.woorank.api.ops.result.Error;
import lombok.Getter;

public class InvalidResultException extends Throwable {

    @Getter
    private final Error error;

    public InvalidResultException(Error error) {
        super(String.format("%d: %s", error.getStatus(), error.getMessage()));

        this.error = error;
    }
}
