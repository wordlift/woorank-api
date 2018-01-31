package com.woorank.api.ops;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.woorank.api.utils.JsonEntity;
import com.woorank.api.utils.JsonUtils;
import com.woorank.api.utils.SingletonMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.Collections;

/**
 * Operations: Create Project
 * <p>
 * Represents an API {@link Operation} to create a project on Woorank.
 *
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Slf4j
public class CreateProjectOperation extends AbstractOperation<HttpPost, String> {

    /**
     * The API relative URL.
     *
     * @since 1.0.0
     */
    private final static URI RELATIVE_URI = URI.create("/projects");

    /**
     * The project's domain.
     *
     * @since 1.0.0
     */
    private final String domain;

    /**
     * {@inheritDoc}
     */
    public HttpPost toHttpRequest(URI baseUri) throws JsonProcessingException {

        if (log.isDebugEnabled()) log.debug("Preparing a request with domain " + this.domain + "...");

        // Create a post request.
        val request = new HttpPost();
        request.setURI(baseUri.resolve(RELATIVE_URI));
        request.setEntity(new JsonEntity(Collections.singletonMap("url", this.domain)));

        return request;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getResult(HttpEntity entity) throws IOException, InvalidResultException {

        // Get the single value from the map `{"resolveUrl": "example.org"}`.
        val content = EntityUtils.toString(entity);

        try {
            return SingletonMap.getValue(content);
        } catch (Exception e) {
            if (log.isErrorEnabled()) log.error("An error occurred: " + content);

            val error = getError(content);

            throw new InvalidResultException(error);
        }

    }

}
