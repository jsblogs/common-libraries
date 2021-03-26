package com.jsblogs.ratelimiter.api;

public interface IRequestMatcher {

    boolean matches(IRequest request);
}
