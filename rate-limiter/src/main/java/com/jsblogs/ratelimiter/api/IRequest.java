package com.jsblogs.ratelimiter.api;

public interface IRequest {

    String getPath();

    String httpMethod();

    String getUniqueId();
}
