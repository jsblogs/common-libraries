package com.jsblogs.ratelimiter.api.internal;

import com.jsblogs.ratelimiter.api.IRequest;
import com.jsblogs.ratelimiter.api.IUniqueIdFetcher;
import com.jsblogs.ratelimiter.enums.HttpMethod;

import javax.servlet.http.HttpServletRequest;

public class HttpRequest implements IRequest {
    private final String path;
    private final HttpMethod method;
    private final String id;

    public HttpRequest(HttpServletRequest req, IUniqueIdFetcher idFetcher) {
        this.path = req.getPathInfo();
        this.method = HttpMethod.from(req.getMethod());
        this.id = idFetcher.fetchId(req);
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public HttpMethod method() {
        return method;
    }

    @Override
    public String getUniqueId() {
        return id;
    }
}
