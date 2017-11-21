package com.woorank.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.apache.http.HttpEntity;
import org.apache.http.HttpMessage;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 */
public class App {

    private final static String WOORANK_API = "https://api.woorank.com/projects";
    private final static String WOORANK_API_AUTH = "456b66d0ed4e3c9a2d7b559e40ce9034";
    private final static String WOORANK_API_KEY = "FKYPxidUP41A9W20krDWO4UZw56c7BkNanNYd8Q5";

    public static void main(String[] args) {
        System.out.println("Hello World!");

        try {
            doCall();
        } catch (Exception e) {

        }
    }

    private static void doCall() throws Exception {

        val uri = URI.create(WOORANK_API);
        val signer = new ClientSigner(WOORANK_API_AUTH, WOORANK_API_KEY);

        val createProject = new CreateProjectOperation("wordlift.io");
        val resolvedUrl = new Client(uri, signer).execute(createProject);

        System.out.println("Resolved URL :: " + resolvedUrl);

        val getKeywordsData = new GetKeywordsDataOperation("wordlift.io");
        val keywords = new Client(uri, signer).execute(getKeywordsData);

        keywords.forEach(x-> System.out.println(x.getKeyword()));

        System.out.println(keywords.toString());

    }

}
