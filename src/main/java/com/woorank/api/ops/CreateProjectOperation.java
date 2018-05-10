package com.woorank.api.ops;

import com.woorank.api.ops.exceptions.InvalidResponseException;
import com.woorank.api.ops.exceptions.MaximumNumberOfAdvancedReviewsReachedException;
import com.woorank.api.ops.exceptions.ProjectAlreadyExistsException;
import com.woorank.api.ops.exceptions.UrlCannotBeResolvedException;
import com.woorank.api.ops.result.CreateProjectResult;
import com.woorank.api.utils.JsonEntity;
import com.woorank.api.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;

import java.net.URI;
import java.util.HashMap;

/**
 * Operations: Create Project
 * <p>
 * Represents an API {@link Operation} to create a project on WooRank.
 *
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Slf4j
public class CreateProjectOperation extends AbstractOperation<HttpPost, CreateProjectResult> {

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
     * If true, indicates that the project should have an advanced review.
     *
     * @since 1.1.0
     */
    private final boolean advanced;

    /**
     * Create a {@link CreateProjectOperation} instance.
     *
     * @param domain The target domain, without the http protocol (e.g. bbc.com).
     * @since 1.1.0
     */
    public CreateProjectOperation(String domain) {
        this.domain = domain;
        this.advanced = false;
    }

    /**
     * {@inheritDoc}
     */
    public HttpPost toHttpRequest(URI baseUri) {

        if (log.isDebugEnabled()) log.debug("Preparing a request with domain " + this.domain + "...");

        // Create a post request.
        val request = new HttpPost();
        request.setURI(baseUri.resolve(RELATIVE_URI));
        request.setEntity(getRequestEntity());

        return request;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CreateProjectResult getResult(HttpResponse response) throws Exception {

        val code = response.getStatusLine().getStatusCode();

        // Validate the response status code.
        if (200 != code && 400 != code && 403 != code && 409 != code) throw new InvalidResponseException(response);

        // 400 When the url can not be resolved.
        if (400 == code) throw new UrlCannotBeResolvedException(response, domain);

        // 403 When the maximum number of advanced reviews have been reached.
        if (403 == code) throw new MaximumNumberOfAdvancedReviewsReachedException(response);

        // 403 When the maximum number of advanced reviews have been reached.
        if (409 == code) throw new ProjectAlreadyExistsException(response);

        return getResult(response.getEntity());
    }

    @Override
    public CreateProjectResult getResult(HttpEntity entity) throws Exception {

        return JsonUtils.getReader().forType(CreateProjectResult.class)
                .readValue(entity.getContent());
    }

    /**
     * Prepare the request {@link JsonEntity} using the operation's parameters.
     *
     * @return A {@link JsonEntity}.
     * @since 1.1.0
     */
    @SneakyThrows
    private JsonEntity getRequestEntity() {

        val map = new HashMap<String, Object>();
        map.put("url", this.domain);
        map.put("advanced", this.advanced);

        return new JsonEntity(map);
    }

}
