package com.woorank.api.ops;

import com.woorank.api.ops.exceptions.InvalidResponseException;
import com.woorank.api.ops.exceptions.InvalidResultException;
import com.woorank.api.ops.result.GetKeywordsDataResult;
import com.woorank.api.utils.JsonUtils;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;

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
public class GetKeywordsDataOperation extends AbstractOperation<HttpGet, GetKeywordsDataResult> {

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
        val uri = baseUri.resolve("/projects/" + this.domain + "/serp/keywords-data");

        // Create a builder on the base URI.
        val builder = new URIBuilder(uri);

        // Add the country and language parameters if provided.
        if (null != country) builder.addParameter("country", country);
        if (null != language) builder.addParameter("language", language);

        // Finally build the URI.
        val uriWithParameters = builder.build();

        // Return the request.
        return new HttpGet(uriWithParameters);
    }

    @Override
    public GetKeywordsDataResult getResult(HttpResponse response) throws Exception {
        val code = response.getStatusLine().getStatusCode();

        if (200 != code) throw new InvalidResponseException(response);

        return getResult(response.getEntity());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GetKeywordsDataResult getResult(HttpEntity entity) throws Exception {

        return JsonUtils.getReader().forType(GetKeywordsDataResult.class)
                .readValue(entity.getContent());
    }

}
