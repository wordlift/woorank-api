package com.woorank.api.ops;

import org.apache.http.HttpEntity;
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
    public Boolean getResult(HttpResponse response) {
        return 200 == response.getStatusLine().getStatusCode();
    }

}
