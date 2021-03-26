package com.jsblogs.ratelimiter.api;

import com.jsblogs.ratelimiter.enums.HttpMethod;

public interface IRequest {

    String getPath();

    HttpMethod method();

    String getUniqueId();
}
