package com.woorank.api.ops;

import com.woorank.api.ops.exceptions.InvalidResponseException;
import com.woorank.api.ops.exceptions.ProjectNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;

import java.net.URI;

@RequiredArgsConstructor
public class DeleteProjectOperation extends AbstractOperation<HttpDelete, Void> {

    private final String domain;

    @Override
    public HttpDelete toHttpRequest(URI baseUri) {

        val uri = baseUri.resolve("/projects/" + this.domain);
        val request = new HttpDelete(uri);

        return request;
    }

    @Override
    public Void getResult(HttpResponse response) throws Exception {

        val code = response.getStatusLine().getStatusCode();

        // Validate the status code.
        if (200 != code && 404 != code) throw new InvalidResponseException(response);

        // If code is 404, throw an exception.
        if (404 == code) throw new ProjectNotFoundException(response);

        return null;
    }

}
