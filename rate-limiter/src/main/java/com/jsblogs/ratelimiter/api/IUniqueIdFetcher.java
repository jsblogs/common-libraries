package com.jsblogs.ratelimiter.api;

import javax.servlet.http.HttpServletRequest;

public interface IUniqueIdFetcher {

    String fetchId(HttpServletRequest request);
}
