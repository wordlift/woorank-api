package com.woorank.api.ops;

import com.woorank.api.ops.exceptions.InvalidResponseException;
import lombok.val;
import org.apache.http.HttpVersion;
import org.apache.http.message.BasicHttpResponse;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests: Validate Key.
 * <p>
 * Test the {@link ValidateKeyOperation} class.
 *
 * @since 1.0.0
 */
class ValidateKeyOperationTest {

    @Test
    void testHttpRequestUri() {

        // The test URI.
        val uri = URI.create("http://example.org");


        val op = new ValidateKeyOperation();
        val request = op.toHttpRequest(uri);

        assertEquals(uri, request.getURI(), "Expecting URI to be the one provided.");
        assertEquals("GET", request.getMethod(), "Expecting GET http method.");

    }

    @Test
    void testValidKey() throws InvalidResponseException {

        val op = new ValidateKeyOperation();
        val result = op.getResult(new BasicHttpResponse(HttpVersion.HTTP_1_1, 200, "OK"));

        assertTrue(result, "Expecting result to be true.");

    }

    @Test
    void testInvalidKey() throws InvalidResponseException {

        val op = new ValidateKeyOperation();
        val result = op.getResult(new BasicHttpResponse(HttpVersion.HTTP_1_1, 401, "Not Authorized"));

        assertFalse(result, "Expecting result to be false.");

    }

    @Test
    void testInvalidResponse() {

        val op = new ValidateKeyOperation();

        assertThrows(InvalidResponseException.class
                , () ->
                        op.getResult(new BasicHttpResponse(HttpVersion.HTTP_1_1, 500, "Server Error"))
                , "Expecting InvalidResponseException.");

    }

}