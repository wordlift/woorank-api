package com.woorank.api.client;

import com.woorank.api.Client;
import com.woorank.api.ClientSigner;
import com.woorank.api.ops.CreateProjectOperation;
import com.woorank.api.ops.exceptions.InvalidResultException;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    void testCreateProjectWithInvalidDomain() throws Exception, InvalidResultException {

        val key = System.getProperty("woorank.api.key");

        assertNotNull(key, "Key can't be null, please set the system property");

        val signer = new ClientSigner(key);
        val client = new Client(URI.create("https://api.woorank.com"), signer);

        val op = new CreateProjectOperation("https://invalid.domain");

        val resolved = client.execute(op);

        assertNotNull(resolved, "Resolved domain cannot be null");

    }

}