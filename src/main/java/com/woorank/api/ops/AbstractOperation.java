package com.woorank.api.ops;

import com.woorank.api.ops.result.Error;
import com.woorank.api.ops.result.ErrorResponse;
import com.woorank.api.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpRequestBase;

import java.io.IOException;

@Slf4j
abstract class AbstractOperation<T extends HttpRequestBase, U> implements Operation<T, U> {

    Error getError(String content) {

        try {
            final ErrorResponse response = JsonUtils.getReader()
                    .forType(ErrorResponse.class)
                    .readValue(content);

            return response.getError();

        } catch (IOException e) {

            if (log.isDebugEnabled()) log.debug("An exception occurred: " + e.getMessage(), e);

            return Error.UNKNOWN_ERROR;
        }

    }

}
