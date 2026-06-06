package com.reqres.api.automation.core.listeners;

import com.reqres.api.automation.core.config.ConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private static final Logger log = LoggerFactory.getLogger(RetryAnalyzer.class);
    private static final int MAX_RETRIES = ConfigManager.getInt("retry.max.count", 2);
    private int attempt = 0;

    @Override
    public boolean retry(ITestResult result) {
        if (!ConfigManager.getBoolean("retry.enabled", true)) return false;
        if (attempt < MAX_RETRIES) {
            attempt++;
            log.warn("Retrying [{}/{}]: {}", attempt, MAX_RETRIES, result.getMethod().getMethodName());
            return true;
        }
        return false;
    }
}
