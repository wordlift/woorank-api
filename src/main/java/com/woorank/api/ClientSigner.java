package com.woorank.api;

import lombok.RequiredArgsConstructor;
import org.apache.http.HttpMessage;

@RequiredArgsConstructor
public class ClientSigner implements Signer {

    private final String auth;
    private final String key;

    /**
     * {@inheritDoc}
     */
    @Override
    public void sign(HttpMessage message) {

        message.addHeader("x-api-key", this.key);
        message.addHeader("auth", this.auth);

    }

}
