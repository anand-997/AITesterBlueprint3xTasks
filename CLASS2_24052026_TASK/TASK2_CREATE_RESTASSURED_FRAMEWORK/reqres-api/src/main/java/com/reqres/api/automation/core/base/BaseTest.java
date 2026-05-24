package com.reqres.api.automation.core.base;

import com.reqres.api.automation.core.config.ConfigManager;
import com.reqres.api.automation.core.utils.RequestResponseCapture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

public class BaseTest {
    protected static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    @BeforeSuite(alwaysRun = true)
    public void suiteSetup() {
        log.info("══════════════════════════════════════════");
        log.info("  API AUTOMATION SUITE STARTING");
        log.info("  Environment : {}", System.getProperty("env", "dev"));
        log.info("  Base URI    : {}", ConfigManager.get("api.base.uri", "NOT CONFIGURED"));
        log.info("══════════════════════════════════════════");
    }

    @AfterSuite(alwaysRun = true)
    public void suiteTeardown() {
        log.info("══════════════════════════════════════════");
        log.info("  API AUTOMATION SUITE COMPLETE");
        log.info("══════════════════════════════════════════");
    }

    @BeforeClass(alwaysRun = true)
    public void classSetup() {
        log.info("── Test class: [{}] ──", getClass().getSimpleName());
    }

    @AfterClass(alwaysRun = true)
    public void classTeardown() {
        log.debug("Completed class: {}", getClass().getSimpleName());
    }

    @BeforeMethod(alwaysRun = true)
    public void methodSetup() {
        RequestResponseCapture.clear();
    }

    @AfterMethod(alwaysRun = true)
    public void methodTeardown() {
        RequestResponseCapture.clear();
    }
}
