package com.reqres.api.automation.endpoints;

import com.reqres.api.automation.core.base.BaseAPI;
import com.reqres.api.automation.core.config.ConfigManager;
import com.reqres.api.automation.core.utils.RequestResponseCapture;
import com.reqres.api.automation.core.utils.TokenManager;
import io.restassured.response.Response;

public class SmokeEndpoints extends BaseAPI {
    private static final String ENDPOINT_1 = ConfigManager.get("api.path.smoke.endpoint1", "/api/users");
    private static final String ENDPOINT_2 = ConfigManager.get("api.path.smoke.endpoint2", "/api/users/2");

    public Response getUsers() {
        RequestResponseCapture.captureRequest("GET", ENDPOINT_1, null, null);
        Response resp = getAuthRequestSpec("x-api-key", TokenManager.getToken()).get(ENDPOINT_1);
        RequestResponseCapture.captureResponse(resp);
        return resp;
    }

    public Response getSingleUser() {
        RequestResponseCapture.captureRequest("GET", ENDPOINT_2, null, null);
        Response resp = getAuthRequestSpec("x-api-key", TokenManager.getToken()).get(ENDPOINT_2);
        RequestResponseCapture.captureResponse(resp);
        return resp;
    }
}
