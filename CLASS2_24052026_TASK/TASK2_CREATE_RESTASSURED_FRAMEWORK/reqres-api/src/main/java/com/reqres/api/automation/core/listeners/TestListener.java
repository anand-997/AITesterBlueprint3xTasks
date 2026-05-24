package com.reqres.api.automation.core.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.reqres.api.automation.core.config.ConfigManager;
import com.reqres.api.automation.core.utils.ExtentTestManager;
import com.reqres.api.automation.core.utils.RequestResponseCapture;
import org.testng.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener, ISuiteListener {
    private static volatile ExtentReports extentReports;

    @Override
    public synchronized void onStart(ISuite suite) {
        String env = System.getProperty("env", "dev");
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String reportPath = "reports/API_Report_" + env.toUpperCase() + "_" + timestamp + ".html";
        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("ReqRes API Automation Report");
        spark.config().setReportName("ReqRes API Tests — " + env.toUpperCase());
        spark.config().setTimeStampFormat("yyyy-MM-dd HH:mm:ss");
        extentReports = new ExtentReports();
        extentReports.attachReporter(spark);
        extentReports.setSystemInfo("Environment", env.toUpperCase());
        extentReports.setSystemInfo("Base URI",    ConfigManager.get("api.base.uri", "N/A"));
        extentReports.setSystemInfo("Java",        System.getProperty("java.version"));
        extentReports.setSystemInfo("OS",          System.getProperty("os.name"));
        extentReports.setSystemInfo("Suite",       suite.getName());
    }

    @Override
    public synchronized void onFinish(ISuite suite) {
        if (extentReports != null) extentReports.flush();
    }

    @Override public void onStart(ITestContext context)  {}
    @Override public void onFinish(ITestContext context) {}

    @Override
    public void onTestStart(ITestResult result) {
        String description = result.getMethod().getDescription();
        String testName = (description != null && !description.isBlank())
            ? description : result.getMethod().getMethodName();
        ExtentTest test = extentReports.createTest(
            result.getTestClass().getRealClass().getSimpleName() + " ➜ " + testName);
        String[] groups = result.getMethod().getGroups();
        if (groups != null && groups.length > 0) test.assignCategory(groups);
        ExtentTestManager.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = ExtentTestManager.extentTest.get();
        if (test != null) test.log(Status.PASS, "PASSED");
        ExtentTestManager.clear();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = ExtentTestManager.extentTest.get();
        if (test == null) { ExtentTestManager.clear(); return; }
        test.log(Status.FAIL,
            "<b>Class:</b> "  + result.getTestClass().getRealClass().getName() + "<br>" +
            "<b>Method:</b> " + result.getMethod().getMethodName());
        if (result.getThrowable() != null) test.fail(result.getThrowable());
        attachApiDetails(test);
        ExtentTestManager.clear();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = ExtentTestManager.extentTest.get();
        if (test != null) {
            String reason = result.getThrowable() != null ? result.getThrowable().getMessage() : "Skipped";
            test.log(Status.SKIP, reason);
        }
        ExtentTestManager.clear();
    }

    private void attachApiDetails(ExtentTest test) {
        String httpMethod   = RequestResponseCapture.getMethod();
        String url          = RequestResponseCapture.getUrl();
        String requestBody  = RequestResponseCapture.getPayload();
        String responseBody = RequestResponseCapture.getResponseBody();
        int    code         = RequestResponseCapture.getStatusCode();
        String curl         = RequestResponseCapture.generateCurl();
        if (!url.isBlank())          test.fail("<b>Request:</b> " + httpMethod + " " + url);
        if (!requestBody.isBlank())  { test.fail("Request Payload:"); test.fail(MarkupHelper.createCodeBlock(requestBody, CodeLanguage.JSON)); }
        if (!responseBody.isBlank()) { test.fail("Response [HTTP " + code + "]:"); test.fail(MarkupHelper.createCodeBlock(responseBody, CodeLanguage.JSON)); }
        if (!curl.isBlank())         { test.fail("Reproduce with cURL:"); test.fail(MarkupHelper.createCodeBlock(curl, CodeLanguage.XML)); }
    }
}
