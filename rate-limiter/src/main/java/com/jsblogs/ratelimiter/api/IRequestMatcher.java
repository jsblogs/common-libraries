package com.jsblogs.ratelimiter.api;

import javax.servlet.http.HttpServletRequest;

public interface IRequestMatcher {

    boolean matches(IRequest request);
}
