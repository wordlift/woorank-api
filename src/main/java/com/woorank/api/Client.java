package com.woorank.api;

import com.woorank.api.ops.exceptions.InvalidResultException;
import com.woorank.api.ops.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.net.URI;

/**
 * API Client.
 * <p>
 * The API Client performs {@link Operation}s towards Woorank.
 *
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Slf4j
public class Client {

    /**
     * The base URI for WooRank's API.
     *
     * @since 1.0.0
     */
    private final URI uri;

    /**
     * A {@link Signer} instance to sign the requests.
     *
     * @since 1.0.0
     */
    private final Signer signer;

    /**
     * Create a Client instance using the default URI.
     *
     * @param signer The {@link Signer} instance.
     * @since 1.1.0
     */
    public Client(Signer signer) {

        this.uri = URI.create("https://api.woorank.com");
        this.signer = signer;

    }

    /**
     * Execute operations.
     * <p>
     * Execute the provided {@link Operation} towards Woorank's APIs.
     *
     * @param operation The {@link Operation} to perform.
     * @param <U>       The {@link Operation} result type.
     * @return The {@link Operation}'s result.
     * @throws Exception thrown in case of http errors or response conversion errors.
     * @since 1.0.0
     */
    public <U> U execute(Operation<? extends HttpRequestBase, U> operation) throws Exception, InvalidResultException {

        if (log.isTraceEnabled()) log.trace("Executing operation " + operation.toString() + "...");

        // Use an auto closeable client.
        try (CloseableHttpClient client = HttpClients.createDefault()) {

            // Get the http request for the operation.
            val request = operation.toHttpRequest(uri);
            signer.sign(request);

            // Execute the request and get the response.
            try (val response = client.execute(request)) {

                // Have the operation convert the response into the actual result type.
                return operation.getResult(response);
            }

        }

    }

}
