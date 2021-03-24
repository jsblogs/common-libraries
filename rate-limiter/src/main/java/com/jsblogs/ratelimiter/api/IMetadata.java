package com.jsblogs.ratelimiter.api;

import java.time.Duration;

public interface IMetadata {

    /**
     * @return API path for which current rate limit is configured.
     */
    String getApiPath();

    /**
     * @return Unique id to identify the client
     */
    String getClient();

    /**
     * @return allowed rate limit for current path.
     */
    Long getAllowedApiLimit();

    /**
     * @return duration of one particular window.
     */
    Duration getWindowDuration();

    /**
     * @return Total remaining API calls.
     */
    Long getRemainingApiLimits();

    /**
     * @return total time remaining till next rate limit window.
     */
    Long getRemainingTime();
}
