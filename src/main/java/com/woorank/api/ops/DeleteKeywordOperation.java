package com.woorank.api.ops;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.tools.javac.util.ArrayUtils;
import com.woorank.api.utils.JsonEntity;
import com.woorank.api.utils.SingletonMap;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.Collections;

/**
 * Operations: Delete Keyword
 * <p>
 * Represents an API {@link Operation} to create a keyword on Woorank.
 *
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class DeleteKeywordOperation implements Operation<HttpDelete, String> {

    /**
     * The API relative URL.
     *
     * @since 1.0.0
     */
    private final static String URI = "/projects/%s/keywords?keywords=%s&country=%s&language=%s";

    /**
     * The project's domain.
     *
     * @since 1.0.0
     */
    private final String domain;

    private final KeywordRequest request;

    /**
     * {@inheritDoc}
     */
    public HttpDelete toHttpRequest(URI baseUri) throws JsonProcessingException {

        // Get the URI for the requested domain.
        val keywords = this.request.getKeywords().toArray(new String[this.request.getKeywords().size()]);
        val uri = String.format(URI, this.domain,  keywords);

        // Delete a post request.
        val request = new HttpDelete();
        request.setURI(baseUri.resolve(uri));
        return request;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getResult(HttpEntity entity) throws IOException {

        // Get the single value from the map `{"resolveUrl": "example.org"}`.
        val content = EntityUtils.toString(entity);

        return SingletonMap.getValue(content);
    }

}
