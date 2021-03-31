package com.jsblogs.ratelimiter.dto;

import com.jsblogs.ratelimiter.api.IRequest;
import com.jsblogs.ratelimiter.enums.HttpMethod;

import java.util.Objects;

public class CacheKey implements IRequest {
    private String path;
    private HttpMethod method;
    private String uniqueId;

    public CacheKey(String path, HttpMethod method, String uniqueId) {
        this.path = path;
        this.method = method;
        this.uniqueId = uniqueId;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public HttpMethod getMethod() {
        return method;
    }

    @Override
    public String getUniqueId() {
        return uniqueId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CacheKey cacheKey = (CacheKey) o;
        return Objects.equals(path, cacheKey.path) && method == cacheKey.method && Objects.equals(uniqueId, cacheKey.uniqueId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, method, uniqueId);
    }
}
