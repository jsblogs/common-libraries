package com.jsblogs.ratelimiter.api.internal;

import com.jsblogs.ratelimiter.api.IStore;

public final class RateLimiterFactory {

    private RateLimiterFactory() {
    }

    public static IStore createInMemoryStore() {
        return new InMemoryStore();
    }
}
