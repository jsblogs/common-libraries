package com.jsblogs.ratelimiter.api;

public interface IncomingRequest {

    String getPath();

    String getUniqueId();
}
