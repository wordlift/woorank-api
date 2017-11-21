package com.woorank.api;

import com.woorank.api.client.Client;
import com.woorank.api.client.ClientSigner;
import com.woorank.api.ops.CreateProjectOperation;
import com.woorank.api.ops.GetKeywordsDataOperation;
import lombok.val;

import java.net.URI;

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
        val client = new Client(uri, signer);

        val createProject = new CreateProjectOperation("salzburgerland.com");
        val resolvedUrl = client.execute(createProject);

        System.out.println("Resolved URL :: " + resolvedUrl);

        val getKeywordsData = new GetKeywordsDataOperation("salzburgerland.com");
        val keywords = client.execute(getKeywordsData);

        keywords.forEach(x -> System.out.println(x.getKeyword()));

        System.out.println(keywords.toString());

    }

}
