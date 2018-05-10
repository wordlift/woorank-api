package com.woorank.api.client;

import org.apache.http.HttpMessage;

/**
 * Client Signer interface.
 *
 * @since 1.0.0
 */
public interface Signer {

    /**
     * Sign the provided {@link HttpMessage}.
     *
     * @param message The {@link HttpMessage} to sign.
     * @since 1.0.0
     */
    void sign(HttpMessage message);

}
