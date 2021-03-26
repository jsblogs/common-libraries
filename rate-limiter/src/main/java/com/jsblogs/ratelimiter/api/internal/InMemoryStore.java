package com.jsblogs.ratelimiter.api.internal;

import com.jsblogs.ratelimiter.api.IMetadata;
import com.jsblogs.ratelimiter.api.IRequest;
import com.jsblogs.ratelimiter.api.IStore;

class InMemoryStore implements IStore {

    @Override
    public IMetadata getRateLimitMetaData(IRequest request) {
        return null;
    }

    @Override
    public void markVisited(IRequest request) {

    }
}
