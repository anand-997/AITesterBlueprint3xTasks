package com.reqres.api.automation.core.utils;

import com.reqres.api.automation.core.config.ConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TokenManager {
    private static final Logger log = LoggerFactory.getLogger(TokenManager.class);

    private TokenManager() {}

    // Returns the configured x-api-key value from auth.properties
    public static String getToken() {
        String key = ConfigManager.get("auth.api.key");
        if (key == null || key.isBlank()) {
            log.warn("auth.api.key is not configured in auth.properties");
        }
        log.debug("API key retrieved from config");
        return key;
    }

    public static void invalidate() {
        log.debug("invalidate() is a no-op for API key authentication");
    }

    public static void clearThreadToken() {
        log.debug("clearThreadToken() is a no-op for API key authentication");
    }
}
