package com.jsblogs.ratelimiter.api;

public interface IRequestMatcher {

    MatchedResult matches(IRequest request);

    MatchedResult UNMATCHED_REQUEST = new MatchedResult(null);

    class MatchedResult {
        boolean matched;
        IMetadata matchedMetadata;

        public MatchedResult(IMetadata matchedMetadata) {
            this.matchedMetadata = matchedMetadata;
            this.matched = matchedMetadata != null;
        }

        public boolean isMatched() {
            return matched;
        }

        public IMetadata getMatchedMetadata() {
            return matchedMetadata;
        }
    }
}
