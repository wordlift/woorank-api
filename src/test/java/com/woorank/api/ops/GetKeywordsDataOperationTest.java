package com.woorank.api.ops;

import com.woorank.api.ops.exceptions.InvalidResponseException;
import com.woorank.api.ops.exceptions.InvalidResultException;
import lombok.val;
import org.apache.http.HttpVersion;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpResponse;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GetKeywordsDataOperationTest {

    private final URI API_URI = URI.create("http://api.example.org");
    private final String DOMAIN = "domain.example.org";
    private final String COUNTRY = "us";
    private final String LANGUAGE = "en";

    @Test
    void testWithNoParameters() throws URISyntaxException {

        val op = new GetKeywordsDataOperation(DOMAIN);
        val request = op.toHttpRequest(API_URI);

        assertEquals(URI.create("http://api.example.org/projects/" + DOMAIN + "/serp/keywords-data"), request.getURI()
                , "Expecting target URI to match.");
        assertEquals("GET", request.getMethod(), "Expecting http method to be GET.");

    }

    @Test
    void testWithCountryAndLanguage() throws URISyntaxException {

        val op = new GetKeywordsDataOperation(DOMAIN, COUNTRY, LANGUAGE);
        val request = op.toHttpRequest(API_URI);

        assertEquals(URI.create("http://api.example.org/projects/" + DOMAIN + "/serp/keywords-data?country=us&language=en"), request.getURI()
                , "Expecting target URI to match.");
        assertEquals("GET", request.getMethod(), "Expecting http method to be GET.");

    }

    @Test
    void testResponse() throws Exception, InvalidResultException {

        val op = new GetKeywordsDataOperation(DOMAIN, COUNTRY, LANGUAGE);
        val response = new BasicHttpResponse(HttpVersion.HTTP_1_1, 200, "OK");
        response.setEntity(new StringEntity("{\n" +
                "  \"data\": [\n" +
                "    {\n" +
                "      \"keyword\": \"Video\",\n" +
                "      \"country\": \"us\",\n" +
                "      \"language\": \"default\",\n" +
                "      \"urls\": [\n" +
                "        {\n" +
                "          \"url\": \"cnn.com\",\n" +
                "          \"results\": [\n" +
                "            {\n" +
                "              \"scrapedAt\": \"2017-12-16T12:53:32.010Z\",\n" +
                "              \"rank\": 5,\n" +
                "              \"foundUrl\": \"http://www.cnn.com/2017/12/15/opinions/federal-judgeships-toobin-opinion/index.html\"\n" +
                "            }\n" +
                "          ]\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}", "UTF-8"));

        val result = op.getResult(response);
        assertEquals(1, result.getData().length, "Expecting one element.");

        val data = result.getData()[0];
        assertEquals("us", data.getCountry(), "Expecting country to match.");
        assertEquals("default", data.getLanguage(), "Expecting country to match.");
        assertEquals("Video", data.getKeyword(), "Expecting country to match.");
        assertEquals(1, data.getUrls().length, "Expecting one element.");

        val url = data.getUrls()[0];
        assertEquals("cnn.com", url.getUrl(), "Expecting url to match.");
        assertEquals(1, url.getResults().length, "Expecting one element.");

        val urlResult = url.getResults()[0];
        assertEquals(
                "http://www.cnn.com/2017/12/15/opinions/federal-judgeships-toobin-opinion/index.html"
                , urlResult.getFoundUrl(), "Expecting url to match.");
        assertEquals(5, (int)urlResult.getRank(), "Expecting rank to match.");
        assertEquals(Instant.parse("2017-12-16T12:53:32.010Z"), urlResult.getScrapedAt()
                , "Expecting scrapedAt to match.");

    }

    @Test
    void testStatusCode500() {

        val op = new GetKeywordsDataOperation(DOMAIN, COUNTRY, LANGUAGE);

        assertThrows(InvalidResponseException.class
                , () ->
                        op.getResult(new BasicHttpResponse(HttpVersion.HTTP_1_1, 500, "Server Error"))
                , "Expecting InvalidResponseException.");

    }

}