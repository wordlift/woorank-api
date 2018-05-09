package com.woorank.api.ops;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;

import java.net.URI;

/**
 * Operation interface
 * <p>
 * Represents an operation that can be performed on Woorank's APIs.
 * <p>
 * Each operation must provide a {@see toHttpRequest} method to convert the operation into an {@link HttpRequestBase}
 * can be executed using an {@link HttpClient} and a {@see getResult} method which transforms the {@link HttpEntity}
 * into an object.
 *
 * @param <T> The type of {@link HttpRequestBase}, e.g. an {@link HttpPost} or an {@link HttpGet}.
 * @param <U> The type of response, can be anything, e.g. {@see String}.
 * @since 1.0.0
 */
public interface Operation<T extends HttpRequestBase, U> {

    /**
     * Convert the {@link Operation} into an {@link HttpRequestBase}.
     *
     * @param baseUri The API base URI, used by the implementing class to set the {@link HttpRequestBase}'s URI.
     * @return An {@link HttpRequestBase} instance.
     * @throws Exception thrown in case of errors creating the {@link HttpRequestBase}.
     * @since 1.0.0
     */
    T toHttpRequest(URI baseUri) throws Exception;

    /**
     * Convert the response's {@link HttpEntity} into the result's object.
     *
     * @param entity The response's {@link HttpEntity}.
     * @return The result.
     * @throws Exception thrown in case of errors converting the response.
     * @since 1.0.0
     */
    U getResult(HttpEntity entity) throws Exception, InvalidResultException;

    /**
     * Convert the {@link HttpResponse} into the result's object.
     *
     * @param response The {@link HttpResponse}.
     * @return The result.
     * @throws Exception thrown in case of errors converting the response.
     * @since 1.0.0
     */
    U getResult(HttpResponse response) throws Exception, InvalidResultException;

}
