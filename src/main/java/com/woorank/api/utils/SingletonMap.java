package com.woorank.api.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.val;

import java.io.IOException;
import java.util.Map;

/**
 * Singleton Map
 * <p>
 * The singleton map util converts a JSON map representation and returns the first value, ignoring the key.
 *
 * @since 1.0.0
 */
public class SingletonMap {

    /**
     * Get a value from the provided JSON representation.
     * <p>
     * The provided string content is converted to a {@link Map} via JSON deserialization and the first value is
     * returned as the requested type.
     *
     * @param content The string content.
     * @param <T> The requested result type.
     * @return The result.
     * @throws IOException thrown in case of errors de-serializing the JSON representation.
     * @since 1.0.0
     */
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
