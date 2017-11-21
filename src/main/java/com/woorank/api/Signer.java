package com.woorank.api;

import org.apache.http.HttpMessage;

public interface Signer {

    void sign(HttpMessage message);

}
