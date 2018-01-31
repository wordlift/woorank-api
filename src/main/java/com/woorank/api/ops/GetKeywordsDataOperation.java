package com.woorank.api.ops;

import com.fasterxml.jackson.core.type.TypeReference;
import com.woorank.api.utils.JsonUtils;
import com.woorank.api.ops.result.Keyword;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Operations: Get Keywords Data
 * <p>
 * Get the keywords data for the specified domain.
 *
 * @since 1.0.0
 */
@AllArgsConstructor
@RequiredArgsConstructor
@Slf4j
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
     * The target country to filter the keywords, or null to get all the keywords.
     *
     * @since 1.0.0
     */
    private String country;

    /**
     * The target language to filter the keywords, or null to get all the keywords.
     *
     * @since 1.0.0
     */
    private String language;

    /**
     * {@inheritDoc}
     */
    @Override
    public HttpGet toHttpRequest(URI baseUri) throws URISyntaxException {

        // Set the domain in the path.
        val path = String.format(URI, this.domain);

        // Create a builder on the base URI.
        val builder = new URIBuilder(baseUri.resolve(path));

        // Add the country and language parameters if provided.
        if (null != country) builder.addParameter("country", country);
        if (null != language) builder.addParameter("language", language);

        // Finally build the URI.
        val uri = builder.build();

        // Return the request.
        return new HttpGet(uri);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Keyword> getResult(HttpEntity entity) throws Exception {

        val typeRef = new TypeReference<List<Keyword>>() {
        };

        val stream = entity.getContent();

        return JsonUtils.getReader()
                .forType(typeRef)
                .readValue(stream);
    }

}
