package com.woorank.api.ops;

import com.fasterxml.jackson.core.type.TypeReference;
import com.woorank.api.utils.JsonUtils;
import com.woorank.api.ops.result.Keyword;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;

import java.net.URI;
import java.util.List;

/**
 * Operations: Get Keywords Data
 * <p>
 * Get the keywords data for the specified domain.
 *
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class GetKeywordsDataOperation implements Operation<HttpGet, List<Keyword>> {

    /**
     * The API path.
     *
     * @since 1.0.0
     */
    private final static String URI = "/projects/%s/keywords-data";

    /**
     * The project's domain.
     *
     * @since 1.0.0
     */
    private final String domain;

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Keyword> getResult(HttpEntity entity) throws Exception {

        val typeRef = new TypeReference<List<Keyword>>() {
        };

        return JsonUtils.getReader()
                .forType(typeRef)
                .readValue(entity.getContent());
    }

}
