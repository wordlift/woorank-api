package com.woorank.api.ops;

import com.woorank.api.ops.exceptions.InvalidResponseException;
import com.woorank.api.ops.exceptions.ProjectNotFoundException;
import com.woorank.api.ops.result.RetrieveProjectResult;
import com.woorank.api.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import java.net.URI;
import java.net.URL;

@RequiredArgsConstructor
@Slf4j
public class RetrieveProjectOperation extends AbstractOperation<HttpGet, RetrieveProjectResult> {

    /**
     * The API relative URL.
     *
     * @since 1.0.0
     */
    private final static String RELATIVE_URI = "/websites/%s";

    private final URL url;

    @Override
    public HttpGet toHttpRequest(URI baseUri) {

        val id = this.url.getHost().replaceFirst("^www\\.", "");

        if (log.isDebugEnabled()) log.debug("Preparing a request with id " + id + "...");

        // Create a post request.
        val request = new HttpGet();
        request.setURI(baseUri.resolve(String.format(RELATIVE_URI, id)));

        return request;
    }

    @Override
    public RetrieveProjectResult getResult(HttpResponse response) throws Exception {

        val code = response.getStatusLine().getStatusCode();

        // Validate the response status code.
        if (200 != code && 404 != code) throw new InvalidResponseException(response);

        // 400 When the url can not be resolved.
        if (404 == code) throw new ProjectNotFoundException(response);

        return getResult(response.getEntity());
    }


    @Override
    public RetrieveProjectResult getResult(HttpEntity entity) throws Exception {

        return JsonUtils.getReader().forType(RetrieveProjectResult.class)
                .readValue(entity.getContent());
    }

}
