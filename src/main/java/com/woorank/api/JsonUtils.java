package com.woorank.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;

/**
 * See https://stackoverflow.com/questions/3907929/should-i-declare-jacksons-objectmapper-as-a-static-field
 */
class JsonUtils {

    private final static ObjectMapper mapper = new ObjectMapper()
            // Register the Java 8 Date/Time classes support.
            .registerModule(new JavaTimeModule());

    @Getter
    private final static ObjectReader reader = mapper.reader();

    @Getter
    private final static ObjectWriter writer = mapper.writer();

}
