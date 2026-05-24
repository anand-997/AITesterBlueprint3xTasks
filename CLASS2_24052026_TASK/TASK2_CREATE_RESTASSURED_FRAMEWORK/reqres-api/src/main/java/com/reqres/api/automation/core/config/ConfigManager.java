package com.reqres.api.automation.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static final Logger log = LoggerFactory.getLogger(ConfigManager.class);
    private static final Properties props = new Properties();

    static {
        loadFile("config/config.properties");
        loadFile("config/api.properties");
        loadFile("config/auth.properties");
        String env = System.getProperty("env", "dev");
        loadFile("environments/" + env + ".properties");
        log.info("ConfigManager initialized for environment: [{}]", env);
    }

    private static void loadFile(String classpathPath) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        try (InputStream in = cl.getResourceAsStream(classpathPath)) {
            if (in != null) {
                props.load(in);
                log.debug("Loaded: {}", classpathPath);
            } else {
                log.debug("Not found on classpath (skipped): {}", classpathPath);
            }
        } catch (IOException e) {
            log.error("Failed to load: {}", classpathPath, e);
            throw new ExceptionInInitializerError("Config load failed: " + classpathPath);
        }
    }

    public static String get(String key) {
        String value = System.getProperty(key, props.getProperty(key));
        if (value == null) log.warn("Property not found: [{}]", key);
        return value;
    }

    public static String get(String key, String defaultValue) {
        String value = System.getProperty(key, props.getProperty(key));
        return value != null ? value.trim() : defaultValue;
    }

    public static int getInt(String key, int defaultValue) {
        String value = get(key);
        if (value == null) return defaultValue;
        try { return Integer.parseInt(value.trim()); }
        catch (NumberFormatException e) {
            log.warn("Cannot parse int for key [{}], value [{}]. Using default: {}", key, value, defaultValue);
            return defaultValue;
        }
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        String value = get(key);
        return value != null ? Boolean.parseBoolean(value.trim()) : defaultValue;
    }
}
