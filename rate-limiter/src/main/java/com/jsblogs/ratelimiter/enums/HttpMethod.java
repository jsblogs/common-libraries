package com.jsblogs.ratelimiter.enums;

public enum HttpMethod {
    GET, POST, DELETE, PUT;

    public static HttpMethod from(String methodName) {
        for (HttpMethod m : values()) {
            if (m.toString().equalsIgnoreCase(methodName)) {
                return m;
            }
        }
        return null;
    }
}
