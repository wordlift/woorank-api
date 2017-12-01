package com.woorank.api.ops;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.woorank.api.utils.JsonEntity;
import com.woorank.api.utils.SingletonMap;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;

/**
 * Operations: Create Keyword
 * <p>
 * Represents an API {@link Operation} to create a keyword on Woorank.
 *
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class CreateKeywordOperation implements Operation<HttpPost, String> {

    /**
     * The API relative URL.
     *
     * @since 1.0.0
     */
    private final static String URI = "/projects/%s/keywords";

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
    public HttpPost toHttpRequest(URI baseUri) throws JsonProcessingException {

        // Get the URI for the requested domain.
        val uri = String.format(URI, this.domain);

        // Create a post request.
        val request = new HttpPost();
        request.setURI(baseUri.resolve(uri));
        request.setEntity(new JsonEntity(this.request));

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
