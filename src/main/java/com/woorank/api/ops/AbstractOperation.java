package com.woorank.api.ops;

import com.woorank.api.ops.result.Error;
import com.woorank.api.ops.result.ErrorResponse;
import com.woorank.api.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;

import java.io.IOException;

@Slf4j
abstract class AbstractOperation<T extends HttpRequestBase, U> implements Operation<T, U> {

    /**
     * Get the {@link HttpResponse}'s {@link HttpEntity} and forward it to the `getResult(HttpEntity entity)` function.
     *
     * @param response The {@link HttpResponse}.
     * @since 1.1.0
     */
    @Override
    public U getResult(HttpResponse response) throws Exception, InvalidResultException {
        return getResult(response.getEntity());
    }

    @Override
    public U getResult(HttpEntity entity) throws Exception, InvalidResultException {
        throw new Exception("Not Implemented.");
    }

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
