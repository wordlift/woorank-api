package com.woorank.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import lombok.val;

import java.io.IOException;
import java.util.Map;

public class SingletonMap {

    public static <T> T getValue(String content) throws IOException {

        // Create a type reference for the specified type.
        val typeRef = new TypeReference<Map<String, T>>() {
        };

        // Get the singleton map.
        Map<String, T> value = JsonUtils.getReader().forType(typeRef).readValue(content);

        // Return the first value.
        return value.values().iterator().next();
    }

}
