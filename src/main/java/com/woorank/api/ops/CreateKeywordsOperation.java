package com.woorank.api.ops;

import com.woorank.api.ops.exceptions.InvalidResponseException;
import com.woorank.api.ops.exceptions.InvalidResultException;
import com.woorank.api.utils.JsonEntity;
import com.woorank.api.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;

import java.net.URI;
import java.util.HashMap;

@RequiredArgsConstructor
public class CreateKeywordsOperation extends AbstractOperation<HttpPost, Boolean> {

    private final String domain;
    private final String[] keywords;
    private final String country;
    private final String language;

    public CreateKeywordsOperation(String domain, String[] keywords, String country) {

        this.domain = domain;
        this.keywords = keywords;
        this.country = country;
        this.language = "default";

    }

    @Override
    public HttpPost toHttpRequest(URI baseUri) {

        val uri = baseUri.resolve("/projects/" + this.domain + "/serp/keywords");
        val post = new HttpPost(uri);
        post.setEntity(getJsonEntity());

        return post;
    }

    @Override
    public Boolean getResult(HttpResponse response) {

        val code = response.getStatusLine().getStatusCode();

        return 200 == code;
    }

    @SneakyThrows
    private JsonEntity getJsonEntity() {

        val params = new HashMap<String, Object>();
        params.put("keywords", this.keywords);
        params.put("country", this.country);
        params.put("language", this.language);

        return new JsonEntity(params);
    }

}
