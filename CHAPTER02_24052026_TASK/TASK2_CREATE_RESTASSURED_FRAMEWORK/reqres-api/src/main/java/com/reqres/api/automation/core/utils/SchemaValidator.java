package com.reqres.api.automation.core.utils;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SchemaValidator {
    private static final Logger log = LoggerFactory.getLogger(SchemaValidator.class);
    private static final String SCHEMA_DIR = "schemas/";

    private SchemaValidator() {}

    public static void validate(Response response, String schemaFile) {
        log.debug("Validating schema: {}", schemaFile);
        response.then().assertThat()
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(SCHEMA_DIR + schemaFile));
        log.debug("Schema validation passed: {}", schemaFile);
    }
}
