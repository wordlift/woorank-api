package com.woorank.api;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
public class GetKeywordsDataOperation implements Operation<HttpGet, List<Keyword>> {

    private final static String URI = "/projects/%s/keywords-data";

    private final String domain;

    @Override
    public HttpGet toHttpRequest(URI baseUri) throws Exception {

        // Get the URI for the requested domain.
        val uri = String.format(URI, this.domain);

        // Build the request.
        val request = new HttpGet();
        request.setURI(baseUri.resolve(uri));

        // Finally return the request.
        return request;
    }

    @Override
    public List<Keyword> getResult(HttpEntity entity) throws Exception {

        val typeRef = new TypeReference<List<Keyword>>() {
        };

        return JsonUtils.getReader()
                .forType(typeRef)
                .readValue(entity.getContent());
    }

}
