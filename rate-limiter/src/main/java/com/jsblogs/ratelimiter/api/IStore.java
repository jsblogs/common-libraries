package com.jsblogs.ratelimiter.api;

public interface IStore {

    IMetadata getRateLimitMetaData(IRequest request);
}
