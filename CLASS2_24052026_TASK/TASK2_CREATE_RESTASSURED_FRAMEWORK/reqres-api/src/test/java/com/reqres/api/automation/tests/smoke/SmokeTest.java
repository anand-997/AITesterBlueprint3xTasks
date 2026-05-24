package com.reqres.api.automation.tests.smoke;

import com.reqres.api.automation.core.base.BaseTest;
import com.reqres.api.automation.core.utils.ExtentTestManager;
import com.reqres.api.automation.core.utils.SchemaValidator;
import com.reqres.api.automation.endpoints.SmokeEndpoints;
import com.reqres.api.automation.models.response.SmokeResponse;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SmokeTest extends BaseTest {

    private SmokeEndpoints smokeEndpoints;

    @BeforeClass(alwaysRun = true)
    @Override
    public void classSetup() {
        super.classSetup();
        smokeEndpoints = new SmokeEndpoints();
    }

    @Test(
        description = "GET /api/users returns HTTP 200 with user list",
        groups = {"smoke"},
        priority = 1
    )
    public void getUsers_validRequest_returns200() {
        SoftAssert soft = new SoftAssert();

        Response response = smokeEndpoints.getUsers();

        soft.assertEquals(response.getStatusCode(), 200, "Status code");
        SchemaValidator.validate(response, "smoke-response-schema.json");
        SmokeResponse body = response.as(SmokeResponse.class);
        soft.assertNotNull(body, "Response body must not be null");
        ExtentTestManager.logPass("GET /api/users returned HTTP 200");

        soft.assertAll();
    }

    @Test(
        description = "GET /api/users/2 returns HTTP 200 with single user",
        groups = {"smoke"},
        priority = 2
    )
    public void getSingleUser_validRequest_returns200() {
        SoftAssert soft = new SoftAssert();

        Response response = smokeEndpoints.getSingleUser();

        soft.assertEquals(response.getStatusCode(), 200, "Status code");
        SchemaValidator.validate(response, "smoke-response-schema.json");
        SmokeResponse body = response.as(SmokeResponse.class);
        soft.assertNotNull(body, "Response body must not be null");
        ExtentTestManager.logPass("GET /api/users/2 returned HTTP 200");

        soft.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    @Override
    public void methodTeardown() {
        super.methodTeardown();
    }
}
