package com.woorank.api;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpRequestBase;

import java.net.URI;

public interface Operation<T extends HttpRequestBase, U> {

    T toHttpRequest(URI baseUri) throws Exception;

    U getResult(HttpEntity entity) throws Exception;

}
