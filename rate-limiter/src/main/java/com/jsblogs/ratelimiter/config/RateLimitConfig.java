package com.jsblogs.ratelimiter.config;

import com.jsblogs.ratelimiter.api.IMetadata;
import com.jsblogs.ratelimiter.api.IRequestMatcher;
import com.jsblogs.ratelimiter.api.IStore;
import com.jsblogs.ratelimiter.api.IUniqueIdFetcher;
import com.jsblogs.ratelimiter.api.internal.RateLimiterFactory;
import com.jsblogs.ratelimiter.utils.ApplicationUtils;

import static com.jsblogs.ratelimiter.utils.ApplicationUtils.urlMatch;

public final class RateLimitConfig {
    private final IStore store;
    private final IRequestMatcher requestMatcher;
    private final IUniqueIdFetcher idFetcher;

    public RateLimitConfig(IStore store, IRequestMatcher requestMatcher,
                           IUniqueIdFetcher idFetcher) {
        this.store = store;
        this.requestMatcher = requestMatcher;
        this.idFetcher = idFetcher;
    }

    public IStore getStore() {
        return store;
    }

    public IRequestMatcher getRequestMatcher() {
        return requestMatcher;
    }

    public IUniqueIdFetcher getIdFetcher() {
        return idFetcher;
    }

    public static Builder create() {
        return new Builder();
    }

    private static class Builder {
        private IStore store;
        private IRequestMatcher requestMatcher;
        private IUniqueIdFetcher idFetcher;

        private Builder() {
            this.store = RateLimiterFactory.createInMemoryStore();
            this.idFetcher = req -> req.getHeader("client-id");
            this.requestMatcher = req -> {
                IMetadata[] metadata = store.getAllConfiguredApis();
                IRequestMatcher.MatchedResult matchedMetadata = IRequestMatcher.UNMATCHED_REQUEST;
                for (IMetadata m : metadata) {
                    if (urlMatch(m.getApiPath(), req.getPath())
                            && m.getMethod().equals(req.getMethod())) {
                        matchedMetadata = new IRequestMatcher.MatchedResult(m);
                        break;
                    }
                }
                return matchedMetadata;
            };
        }

        public Builder setStore(IStore store) {
            this.store = store;
            return this;
        }

        public Builder setRequestMatcher(IRequestMatcher requestMatcher) {
            this.requestMatcher = requestMatcher;
            return this;
        }

        public Builder setIdFetcher(IUniqueIdFetcher idFetcher) {
            this.idFetcher = idFetcher;
            return this;
        }

        public RateLimitConfig build() {
            return new RateLimitConfig(
                    this.store, this.requestMatcher, idFetcher
            );
        }
    }
}
