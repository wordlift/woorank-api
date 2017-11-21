package com.woorank.api.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

/**
 * Http Entities: Json Entity.
 * <p>
 * An JSON {@link HttpEntity} for use with Apache Http Client.
 *
 * @since 1.0.0
 */
public class JsonEntity extends StringEntity {

    /**
     * Create a {@link JsonEntity} instance for the provided object.
     *
     * @param object The target object.
     * @throws JsonProcessingException thrown if the target object cannot be serialized.
     * @since 1.0.0
     */
    public JsonEntity(Object object) throws JsonProcessingException {
        super(JsonUtils.getWriter().writeValueAsString(object), ContentType.APPLICATION_JSON);
    }

}
