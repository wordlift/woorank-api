package com.woorank.api.ops;

import com.woorank.api.ops.exceptions.InvalidResponseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.utils.URIBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class DeleteKeywordsOperation extends AbstractOperation<HttpDelete, Void> {

    private final String domain;

    private final String[] keywords;

    private final String country;

    private final String language;

    public DeleteKeywordsOperation(String domain, String[] keywords, String country) {
        this(domain, keywords, country, "default");
    }

    @Override
    public HttpDelete toHttpRequest(URI baseUri) throws Exception {

        val uri = baseUri.resolve("/websites/" + this.domain + "/serp/keywords");

        // Create a builder on the base URI.
        val builder = new URIBuilder(uri);

        // Add the keywords parameter.
        if (null != keywords) {
            val escaped = Arrays.stream(keywords)
                    .map(k -> {
                        try {
                            return URLEncoder.encode(k, "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            return k;
                        }
                    })
                    .collect(Collectors.joining(","));
            builder.addParameter("keywords", escaped);
        }

        // Add the country and language parameters if provided.
        if (null != country) builder.addParameter("country", country);
        if (null != language) builder.addParameter("language", language);

        // Finally build the URI.
        val uriWithParameters = builder.build();

        if (log.isTraceEnabled()) log.trace("Going to call {}...", uriWithParameters);

        return new HttpDelete(uriWithParameters);
    }

    @Override
    public Void getResult(HttpResponse response) throws InvalidResponseException {

        val code = response.getStatusLine().getStatusCode();

        if (200 != code) throw new InvalidResponseException(response);

        return null;
    }

}
