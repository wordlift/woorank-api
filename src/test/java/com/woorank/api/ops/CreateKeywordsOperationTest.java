package com.woorank.api.ops;

import com.fasterxml.jackson.core.type.TypeReference;
import com.woorank.api.utils.JsonEntity;
import com.woorank.api.utils.JsonUtils;
import lombok.val;
import org.apache.http.HttpVersion;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CreateKeywordsOperationTest {

    private final URI API_URI = URI.create("http://api.example.org");
    private final String DOMAIN = "domain.example.org";
    private final String[] KEYWORDS = new String[]{
            "lorem",
            "ipsum",
            "incidunt"
    };
    private final String COUNTRY = "us";
    private final String LANGUAGE = "en";

    @Test
    void testKeywords() throws IOException {

        val op = new CreateKeywordsOperation(DOMAIN, KEYWORDS, COUNTRY);
        val request = op.toHttpRequest(API_URI);

        assertEquals(URI.create("http://api.example.org/projects/domain.example.org/serp/keywords"), request.getURI()
                , "Expecting API URI to match.");
        assertEquals("POST", request.getMethod(), "Expecting http method to be POST.");

        val entity = request.getEntity();

        assertTrue(entity instanceof JsonEntity, "Expecting request entity to be JsonEntity.");

        val typeRef = new TypeReference<HashMap<String, Object>>() {
        };
        HashMap<String, Object> params = JsonUtils.getReader().forType(typeRef).readValue(entity.getContent());

        assertEquals(Arrays.asList(KEYWORDS), params.get("keywords"), "Expecting keywords to match.");
        assertEquals(COUNTRY, params.get("country"), "Expecting country to match.");
        assertEquals("default", params.get("language"), "Expecting language to be 'default'.");

    }

    @Test
    void testKeywordsAndLanguage() throws IOException {

        val op = new CreateKeywordsOperation(DOMAIN, KEYWORDS, COUNTRY, LANGUAGE);
        val request = op.toHttpRequest(API_URI);

        assertEquals(URI.create("http://api.example.org/projects/domain.example.org/serp/keywords"), request.getURI()
                , "Expecting API URI to match.");
        assertEquals("POST", request.getMethod(), "Expecting http method to be POST.");

        val entity = request.getEntity();

        assertTrue(entity instanceof JsonEntity, "Expecting request entity to be JsonEntity.");

        val typeRef = new TypeReference<HashMap<String, Object>>() {
        };
        HashMap<String, Object> params = JsonUtils.getReader().forType(typeRef).readValue(entity.getContent());

        assertEquals(Arrays.asList(KEYWORDS), params.get("keywords"), "Expecting keywords to match.");
        assertEquals(COUNTRY, params.get("country"), "Expecting country to match.");
        assertEquals(LANGUAGE, params.get("language"), "Expecting language to match.");

    }

    @Test
    void testStatusCode200() {

        val op = new CreateKeywordsOperation(DOMAIN, KEYWORDS, COUNTRY, LANGUAGE);
        val response = new BasicHttpResponse(HttpVersion.HTTP_1_1, 200, "OK");
        response.setEntity(new StringEntity("{\n" +
                "  \"keywords\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"country\": \"ae\",\n" +
                "  \"language\": \"default\"\n" +
                "}", "UTF-8"));
        val result = op.getResult(response);

        assertTrue(result, "Expecting result to be true.");
    }

    @Test
    void testStatusCode500() {

        val op = new CreateKeywordsOperation(DOMAIN, KEYWORDS, COUNTRY, LANGUAGE);
        val response = new BasicHttpResponse(HttpVersion.HTTP_1_1, 500, "Server Errors");
        val result = op.getResult(response);

        assertFalse(result, "Expecting result to be false.");

    }

}