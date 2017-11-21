package com.woorank.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.Collections;

@RequiredArgsConstructor
public class CreateProjectOperation implements Operation<HttpPost, String> {

    private final static URI RELATIVE_URI = URI.create("/projects");

    private final String domain;

    public HttpPost toHttpRequest(URI baseUri) throws JsonProcessingException {

        val request = new HttpPost();
        request.setURI(baseUri.resolve(RELATIVE_URI));
        request.setEntity(new JsonEntity(Collections.singletonMap("url", this.domain)));

        return request;
    }

    @Override
    public String getResult(HttpEntity entity) throws IOException {

        val content = EntityUtils.toString(entity);

        return SingletonMap.getValue(content);
    }

}
