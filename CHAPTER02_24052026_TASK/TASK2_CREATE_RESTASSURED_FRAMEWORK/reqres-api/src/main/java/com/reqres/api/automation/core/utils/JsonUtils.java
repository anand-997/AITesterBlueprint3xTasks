package com.reqres.api.automation.core.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtils {
    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);
    private static final ObjectMapper MAPPER = new ObjectMapper()
        .enable(SerializationFeature.INDENT_OUTPUT);

    private JsonUtils() {}

    public static String prettify(String json) {
        try {
            JsonNode node = MAPPER.readTree(json);
            return MAPPER.writeValueAsString(node);
        } catch (Exception e) {
            log.warn("Could not prettify JSON: {}", e.getMessage());
            return json;
        }
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        try { return MAPPER.readValue(json, clazz); }
        catch (Exception e) { throw new RuntimeException("JSON parse failed: " + e.getMessage(), e); }
    }

    public static String toJson(Object obj) {
        try { return MAPPER.writeValueAsString(obj); }
        catch (Exception e) { throw new RuntimeException("JSON write failed: " + e.getMessage(), e); }
    }
}
