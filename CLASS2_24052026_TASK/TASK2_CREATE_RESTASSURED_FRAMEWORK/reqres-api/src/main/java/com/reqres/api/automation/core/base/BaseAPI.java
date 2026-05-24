package com.reqres.api.automation.core.base;

import com.reqres.api.automation.core.config.ConfigManager;
import com.reqres.api.automation.core.utils.TokenManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseAPI {
    private static final Logger log = LoggerFactory.getLogger(BaseAPI.class);
    private static final RequestSpecification BASE_REQUEST_SPEC;
    private static final ResponseSpecification BASE_RESPONSE_SPEC;

    static {
        String baseUri  = ConfigManager.get("api.base.uri");
        String basePath = ConfigManager.get("api.base.path", "");
        int connectTimeout = ConfigManager.getInt("api.connect.timeout.ms", 10000);
        int socketTimeout  = ConfigManager.getInt("api.socket.timeout.ms", 30000);
        log.info("BaseAPI init — URI: [{}]  Path: [{}]", baseUri, basePath);
        RestAssuredConfig raConfig = RestAssuredConfig.config()
            .httpClient(HttpClientConfig.httpClientConfig()
                .setParam("http.connection.timeout", connectTimeout)
                .setParam("http.socket.timeout", socketTimeout));
        BASE_REQUEST_SPEC = new RequestSpecBuilder()
            .setBaseUri(baseUri)
            .setBasePath(basePath)
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON)
            .setConfig(raConfig)
            .log(LogDetail.ALL)
            .build();
        BASE_RESPONSE_SPEC = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .build();
    }

    // TODO: wire authentication when required
    protected static RequestSpecification getRequestSpec() {
        return RestAssured.given().spec(BASE_REQUEST_SPEC);
    }

    protected static RequestSpecification getAuthRequestSpec() {
        return RestAssured.given()
            .spec(BASE_REQUEST_SPEC)
            .header("Authorization", "Bearer " + TokenManager.getToken());
    }

    protected static RequestSpecification getAuthRequestSpec(String headerName, String headerValue) {
        return RestAssured.given()
            .spec(BASE_REQUEST_SPEC)
            .header(headerName, headerValue);
    }

    protected static ResponseSpecification getResponseSpec() {
        return BASE_RESPONSE_SPEC;
    }
}
