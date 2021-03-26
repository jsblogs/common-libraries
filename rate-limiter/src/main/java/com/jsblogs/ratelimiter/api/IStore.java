package com.jsblogs.ratelimiter.api;

public interface IStore {

    IMetadata getRateLimitMetaData(IRequest request);

    void markVisited(IRequest request);
}
