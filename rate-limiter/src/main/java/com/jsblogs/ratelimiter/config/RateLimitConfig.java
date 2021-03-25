package com.jsblogs.ratelimiter.config;

import com.jsblogs.ratelimiter.api.IStore;
import com.jsblogs.ratelimiter.api.internal.RateLimiterFactory;

public final class RateLimitConfig {
    private final IStore store;

    public RateLimitConfig(IStore store) {
        this.store = store;
    }

    public IStore getStore() {
        return store;
    }

    public static Builder create() {
        return new Builder();
    }

    private static class Builder {
        private IStore store;

        private Builder() {
            this.store = RateLimiterFactory.createInMemoryStore();
        }

        public Builder setStore(IStore store) {
            this.store = store;
            return this;
        }

        public RateLimitConfig build() {
            return new RateLimitConfig(this.store);
        }
    }
}
