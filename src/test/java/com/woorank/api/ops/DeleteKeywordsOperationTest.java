package com.woorank.api.ops;

import com.woorank.api.ops.exceptions.InvalidResponseException;
import com.woorank.api.ops.exceptions.ProjectNotFoundException;
import lombok.val;
import org.apache.http.HttpVersion;
import org.apache.http.message.BasicHttpResponse;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

class DeleteKeywordsOperationTest {

    private final URI API_URI = URI.create("http://api.example.org");
    private final String DOMAIN = "domain.example.org";
    private final String COUNTRY = "us";
    private final String LANGUAGE = "en";

    @Test
    void testRequestOneKeyword() throws Exception {

        val keywords = new String[]{"lorem"};
        val op = new DeleteKeywordsOperation(DOMAIN, keywords, COUNTRY, LANGUAGE);
        val request = op.toHttpRequest(API_URI);

        assertEquals(URI.create("http://api.example.org/projects/domain.example.org/serp/keywords?keywords=lorem&country=us&language=en")
                , request.getURI()
                , "Expecting URI to match.");
        assertEquals("DELETE", request.getMethod(), "Expecting http method to match.");

    }

    @Test
    void testRequestTwoKeywords() throws Exception {

        val keywords = new String[]{"lorem", "ipsum"};
        val op = new DeleteKeywordsOperation(DOMAIN, keywords, COUNTRY, LANGUAGE);
        val request = op.toHttpRequest(API_URI);

        assertEquals(URI.create("http://api.example.org/projects/domain.example.org/serp/keywords?keywords=lorem%2Cipsum&country=us&language=en")
                , request.getURI()
                , "Expecting URI to match.");
        assertEquals("DELETE", request.getMethod(), "Expecting http method to match.");

    }

    @Test
    void testRequestTwoKeywordsNoLanguage() throws Exception {

        val keywords = new String[]{"lorem", "ipsum"};
        val op = new DeleteKeywordsOperation(DOMAIN, keywords, COUNTRY);
        val request = op.toHttpRequest(API_URI);

        assertEquals(URI.create("http://api.example.org/projects/domain.example.org/serp/keywords?keywords=lorem%2Cipsum&country=us&language=default")
                , request.getURI()
                , "Expecting URI to match.");
        assertEquals("DELETE", request.getMethod(), "Expecting http method to match.");

    }

    @Test
    void testStatusCode200() throws Exception {

        val keywords = new String[]{"lorem", "ipsum"};
        val op = new DeleteKeywordsOperation(DOMAIN, keywords, COUNTRY);

        op.getResult(new BasicHttpResponse(HttpVersion.HTTP_1_1, 200, "OK"));

        assertTrue(true, "Expecting to reach this point w/o exceptions.");

    }

    @Test
    void testStatusCode500() {

        val keywords = new String[]{"lorem", "ipsum"};
        val op = new DeleteKeywordsOperation(DOMAIN, keywords, COUNTRY);

        assertThrows(InvalidResponseException.class
                , () ->
                        op.getResult(new BasicHttpResponse(HttpVersion.HTTP_1_1, 500, "Server Error"))
                , "Expecting InvalidResponseException.");

    }

}