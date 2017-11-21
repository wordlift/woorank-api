package com.woorank.api;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.net.URI;

@RequiredArgsConstructor
class Client {

    private final URI uri;
    private final Signer signer;

    <U> U execute(Operation<? extends HttpRequestBase, U> operation) throws Exception {

        try (CloseableHttpClient client = HttpClients.createDefault()) {

            val request = operation.toHttpRequest(uri);
            signer.sign(request);

            try (val response = client.execute(request)) {
                return operation.getResult(response.getEntity());
            } catch (Exception e) {
                System.out.println("An exception occurred :: " + e.getMessage());

                throw e;
            }

        }
    }

//    List<Keyword> getKeywordsData(String domain) {
//
//
//    }

}
