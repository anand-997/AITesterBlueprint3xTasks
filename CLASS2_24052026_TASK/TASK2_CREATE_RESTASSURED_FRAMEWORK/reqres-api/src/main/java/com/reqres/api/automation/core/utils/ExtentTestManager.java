package com.reqres.api.automation.core.utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExtentTestManager {
    private static final Logger log = LoggerFactory.getLogger(ExtentTestManager.class);
    static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private ExtentTestManager() {}

    public static void logPass(String message)    { log(Status.PASS,    message); }
    public static void logFail(String message)    { log(Status.FAIL,    message); }
    public static void logInfo(String message)    { log(Status.INFO,    message); }
    public static void logWarning(String message) { log(Status.WARNING, message); }

    private static void log(Status status, String message) {
        ExtentTest test = extentTest.get();
        if (test != null) test.log(status, message);
        else log.warn("ExtentTest not set for thread [{}] — message dropped: {}",
                Thread.currentThread().getName(), message);
    }

    static void set(ExtentTest test) { extentTest.set(test); }
    static void clear()              { extentTest.remove();  }
}
