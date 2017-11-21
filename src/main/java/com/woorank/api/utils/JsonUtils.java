package com.woorank.api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;

/**
 * JSON Utils
 * <p>
 * The JSON Utils provide instances of {@link ObjectReader} and {@link ObjectWriter}.
 * <p>
 * See https://stackoverflow.com/questions/3907929/should-i-declare-jacksons-objectmapper-as-a-static-field
 */
public class JsonUtils {

    /**
     * The {@link ObjectMapper} instance.
     *
     * @since 1.0.0
     */
    private final static ObjectMapper mapper = new ObjectMapper()
            // Register the Java 8 Date/Time classes support.
            .registerModule(new JavaTimeModule());

    /**
     * The {@link ObjectReader} instance.
     *
     * @since 1.0.0
     */
    @Getter
    private final static ObjectReader reader = mapper.reader();

    /**
     * The {@link ObjectWriter} instance.
     *
     * @since 1.0.0
     */
    @Getter
    private final static ObjectWriter writer = mapper.writer();

}
