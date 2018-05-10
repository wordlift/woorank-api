package com.woorank.api.ops;

import com.woorank.api.ops.exceptions.InvalidResponseException;
import com.woorank.api.ops.exceptions.ProjectNotFoundException;
import lombok.val;
import org.apache.http.HttpVersion;
import org.apache.http.message.BasicHttpResponse;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

class DeleteProjectOperationTest {

    private final URI API_URI = URI.create("http://api.example.org");
    private final String DOMAIN = "domain.example.org";

    @Test
    void testRequest() {

        val op = new DeleteProjectOperation(DOMAIN);
        val request = op.toHttpRequest(API_URI);

        assertEquals(URI.create("http://api.example.org/projects/domain.example.org")
                , request.getURI()
                , "Expecting URI to match.");
        assertEquals("DELETE", request.getMethod(), "Expecting http method to match.");

    }

    @Test
    void testStatusCode200() throws Exception {

        val op = new DeleteProjectOperation(DOMAIN);
        op.getResult(new BasicHttpResponse(HttpVersion.HTTP_1_1, 200, "OK"));

        assertTrue(true, "Expecting to reach this point w/o exceptions.");

    }

    @Test
    void testStatusCode404() {
        val op = new DeleteProjectOperation(DOMAIN);

        assertThrows(ProjectNotFoundException.class
                , () ->
                        op.getResult(new BasicHttpResponse(HttpVersion.HTTP_1_1, 404, "Not Found"))
                , "Expecting ProjectNotFoundException.");
    }

    @Test
    void testStatusCode500() {
        val op = new DeleteProjectOperation(DOMAIN);

        assertThrows(InvalidResponseException.class
                , () ->
                        op.getResult(new BasicHttpResponse(HttpVersion.HTTP_1_1, 500, "Server Error"))
                , "Expecting InvalidResponseException.");

    }

}