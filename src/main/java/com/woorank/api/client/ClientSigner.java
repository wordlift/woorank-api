package com.woorank.api.client;

import lombok.RequiredArgsConstructor;
import org.apache.http.HttpMessage;

/**
 * Client Signer
 * <p>
 * Signs {@link HttpMessage}s with the authentication headers.
 *
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class ClientSigner implements Signer {

    /**
     * The api key.
     *
     * @since 1.0.0
     */
    private final String key;

    /**
     * {@inheritDoc}
     */
    @Override
    public void sign(HttpMessage message) {

        message.addHeader("x-api-key", this.key);

    }

}
