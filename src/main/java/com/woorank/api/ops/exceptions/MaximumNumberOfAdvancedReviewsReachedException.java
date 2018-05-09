package com.woorank.api.ops.exceptions;

import org.apache.http.HttpResponse;

public class MaximumNumberOfAdvancedReviewsReachedException extends AbstractErrorException {

    public MaximumNumberOfAdvancedReviewsReachedException(HttpResponse response) {
        super(response);
    }

}
