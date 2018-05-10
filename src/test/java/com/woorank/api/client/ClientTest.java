package com.woorank.api.client;

import com.woorank.api.ops.CreateKeywordsOperation;
import com.woorank.api.ops.CreateProjectOperation;
import com.woorank.api.ops.DeleteProjectOperation;
import com.woorank.api.ops.GetKeywordsDataOperation;
import com.woorank.api.ops.exceptions.InvalidResultException;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClientTest {

    private final String DOMAIN = "example.com";
    private final String COUNTRY = "us";
    private final String LANGUAGE = "en";

    @BeforeEach
    @SneakyThrows
    void setUp() {

        val op = new DeleteProjectOperation(DOMAIN);
        getClient().execute(op);

    }

    @Test
    @EnabledIfSystemProperty(named = "woorank.api.key", matches = ".+")
    void testIntegration() throws Exception, InvalidResultException {

        val client = getClient();

        val op1 = new CreateProjectOperation("https://example.com", true);
        val result1 = client.execute(op1);

        assertEquals(DOMAIN, result1.getData().getId(), "Expecting the id to match the domain.");
        assertTrue(result1.getData().getReview().isAdvanced(), "Expecting the advanced flag to be true.");

        val op2 = new CreateKeywordsOperation(DOMAIN, new String[]{
                "example",
                "domain",
                "example domain"
        }, COUNTRY, LANGUAGE);
        val result2 = client.execute(op2);
        assertTrue(result2, "Expecting the CreateKeywordsOperation to be successful.");

        // Allow some time for WooRank to calculate the initial keywords.
        Thread.sleep(5000);

        val op3 = new GetKeywordsDataOperation(DOMAIN, COUNTRY, LANGUAGE);
        val result3 = client.execute(op3);
        System.out.printf("Found %d data.", result3.getData().length);

        assertEquals(3, result3.getData().length, "Expecting 3 keyword results.");
    }

    private Client getClient() {

        val key = System.getProperty("woorank.api.key");
        val signer = new ClientSigner(key);
        val client = new Client(signer);

        return client;
    }

}