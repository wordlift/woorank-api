package com.woorank.api.ops;

import com.woorank.api.ops.exceptions.InvalidResponseException;
import lombok.val;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import java.net.URI;

/**
 * Operations: Validate Key.
 * <p>
 * Validates the currently configured key, see https://woorank.github.io/api/#tag/authentication
 *
 * @since 1.1.0
 */
public class ValidateKeyOperation extends AbstractOperation<HttpGet, Boolean> {

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpGet toHttpRequest(URI baseUri) {
        return new HttpGet(baseUri);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean getResult(HttpResponse response) throws InvalidResponseException {

        // Get the status code.
        val code = response.getStatusLine().getStatusCode();

        // Validate the status code, only 200 and 401 are valid status codes.
        if (200 != code && 401 != code) throw new InvalidResponseException(response);

        // Finally return the result.
        return 200 == code;
    }

}
