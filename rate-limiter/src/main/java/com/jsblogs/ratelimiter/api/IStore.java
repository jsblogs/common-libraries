package com.jsblogs.ratelimiter.api;

/**
 * Interface defines the store used to hold the API call counts. Gets the remaining counts for
 * API call and mark request count visited upon successful request visited.
 */
public interface IStore {

    IMetadata[] getAllConfiguredApis();

    IMetadata getRemainingRateLimit(IRequest request);

    void markVisited(IRequest request);
}
