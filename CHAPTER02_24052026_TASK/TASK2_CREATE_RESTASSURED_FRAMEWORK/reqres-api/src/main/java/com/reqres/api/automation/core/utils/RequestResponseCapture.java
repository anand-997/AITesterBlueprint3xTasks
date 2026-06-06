package com.reqres.api.automation.core.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import java.util.LinkedHashMap;
import java.util.Map;

public class RequestResponseCapture {
    private static final ThreadLocal<String>              method       = new ThreadLocal<>();
    private static final ThreadLocal<String>              url          = new ThreadLocal<>();
    private static final ThreadLocal<String>              payload      = new ThreadLocal<>();
    private static final ThreadLocal<Map<String, String>> headers      = new ThreadLocal<>();
    private static final ThreadLocal<String>              responseBody = new ThreadLocal<>();
    private static final ThreadLocal<Integer>             statusCode   = new ThreadLocal<>();
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private RequestResponseCapture() {}

    public static void captureRequest(String httpMethod, String requestUrl,
                                      Object requestPayload, Map<String, String> requestHeaders) {
        method.set(httpMethod);
        url.set(requestUrl);
        headers.set(requestHeaders != null ? new LinkedHashMap<>(requestHeaders) : new LinkedHashMap<>());
        try {
            payload.set(MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(requestPayload));
        } catch (Exception e) {
            payload.set(requestPayload != null ? requestPayload.toString() : "");
        }
    }

    public static void captureResponse(Response response) {
        if (response != null) {
            responseBody.set(response.asPrettyString());
            statusCode.set(response.getStatusCode());
        }
    }

    public static String generateCurl() {
        StringBuilder curl = new StringBuilder("curl -X ")
            .append(getMethod()).append(" \"").append(getUrl()).append("\"");
        Map<String, String> hdrs = headers.get();
        if (hdrs != null)
            hdrs.forEach((k, v) -> curl.append(" \\\n  -H \"").append(k).append(": ").append(v).append("\""));
        String body = getPayload();
        if (!body.isBlank()) curl.append(" \\\n  -d '").append(body).append("'");
        return curl.toString();
    }

    public static String  getMethod()       { return orEmpty(method);       }
    public static String  getUrl()          { return orEmpty(url);          }
    public static String  getPayload()      { return orEmpty(payload);      }
    public static String  getResponseBody() { return orEmpty(responseBody); }
    public static int     getStatusCode()   { return statusCode.get() != null ? statusCode.get() : 0; }

    public static void clear() {
        method.remove(); url.remove(); payload.remove();
        headers.remove(); responseBody.remove(); statusCode.remove();
    }

    private static String orEmpty(ThreadLocal<String> tl) { return tl.get() != null ? tl.get() : ""; }
}
