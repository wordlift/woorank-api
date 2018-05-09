package com.woorank.api.ops;

import com.fasterxml.jackson.core.type.TypeReference;
import com.woorank.api.ops.exceptions.InvalidResponseException;
import com.woorank.api.ops.exceptions.MaximumNumberOfAdvancedReviewsReachedException;
import com.woorank.api.ops.exceptions.ProjectAlreadyExistsException;
import com.woorank.api.ops.exceptions.UrlCannotBeResolvedException;
import com.woorank.api.utils.JsonEntity;
import com.woorank.api.utils.JsonUtils;
import lombok.val;
import org.apache.http.HttpVersion;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CreateProjectOperationTest {

    private final URI API_URI = URI.create("http://api.example.org");
    private final String DOMAIN = "domain.example.org";

    @Test
    void testDomain() throws IOException {

        val op = new CreateProjectOperation(DOMAIN);
        val request = op.toHttpRequest(API_URI);

        assertEquals(URI.create("http://api.example.org/projects"), request.getURI(), "Expecting URI to be .../projects.");
        assertEquals("POST", request.getMethod(), "Expecting http method to be POST.");

        val entity = request.getEntity();

        assertTrue(entity instanceof JsonEntity, "Expecting request entity to be JsonEntity.");

        val typeRef = new TypeReference<HashMap<String, Object>>() {
        };
        HashMap<String, Object> params = JsonUtils.getReader().forType(typeRef).readValue(entity.getContent());

        assertEquals(DOMAIN, params.get("url"), "Expecting url parameter to be domain.example.org.");
        assertFalse((boolean) params.get("advanced"), "Expecting advanced parameter to be false by default.");

    }

    @Test
    void testDomainAndAdvanced() throws IOException {

        val op = new CreateProjectOperation(DOMAIN, true);
        val request = op.toHttpRequest(API_URI);

        assertEquals(URI.create("http://api.example.org/projects"), request.getURI(), "Expecting URI to be .../projects.");
        assertEquals("POST", request.getMethod(), "Expecting http method to be POST.");

        val entity = request.getEntity();

        assertTrue(entity instanceof JsonEntity, "Expecting request entity to be JsonEntity.");

        val typeRef = new TypeReference<HashMap<String, Object>>() {
        };
        HashMap<String, Object> params = JsonUtils.getReader().forType(typeRef).readValue(entity.getContent());

        assertEquals(DOMAIN, params.get("url"), "Expecting url parameter to be domain.example.org.");
        assertTrue((boolean) params.get("advanced"), "Expecting advanced parameter to be false by default.");

    }

    @Test
    void testStatusCode400() {

        val op = new CreateProjectOperation(DOMAIN);
        assertThrows(UrlCannotBeResolvedException.class
                , () ->
                        op.getResult(new BasicHttpResponse(HttpVersion.HTTP_1_1, 400, "Invalid Url."))
                , "Expecting UrlCannotBeResolvedException exception.");

    }

    @Test
    void testStatusCode200() throws Exception {

        val op = new CreateProjectOperation(DOMAIN);
        val result = op.getResult(new StringEntity("{\n" +
                "  \"data\": {\n" +
                "    \"id\": \"bbc.com\",\n" +
                "    \"review\": {\n" +
                "      \"advanced\": true,\n" +
                "      \"lastScore\": 76\n" +
                "    }\n" +
                "  }\n" +
                "}", "UTF-8"));

        assertEquals("bbc.com", result.getData().getId(), "Expecting id to be bbc.com.");
        assertTrue(result.getData().getReview().isAdvanced(), "Expecting isAdvanced to be true.");
        assertEquals(76, result.getData().getReview().getLastScore(), "Expecting lastScore to be 76.");

    }

    @Test
    void testStatusCode403() {

        val op = new CreateProjectOperation(DOMAIN);
        assertThrows(MaximumNumberOfAdvancedReviewsReachedException.class
                , () ->
                        op.getResult(new BasicHttpResponse(HttpVersion.HTTP_1_1, 403, "The maximum number of advanced reviews have been reached."))
                , "Expecting MaximumNumberOfAdvancedReviewsReachedException exception.");

    }

    @Test
    void testStatusCode409() {

        val op = new CreateProjectOperation(DOMAIN);
        assertThrows(ProjectAlreadyExistsException.class
                , () ->
                        op.getResult(new BasicHttpResponse(HttpVersion.HTTP_1_1, 409, "A project for this url already exists."))
                , "Expecting ProjectAlreadyExistsException exception.");

    }

    @Test
    void testStatusCode500() {

        val op = new CreateProjectOperation(DOMAIN);
        assertThrows(InvalidResponseException.class
                , () ->
                        op.getResult(new BasicHttpResponse(HttpVersion.HTTP_1_1, 500, "A project for this url already exists."))
                , "Expecting InvalidResponseException exception.");
    }

}