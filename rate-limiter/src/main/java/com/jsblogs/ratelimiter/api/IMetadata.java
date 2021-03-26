package com.jsblogs.ratelimiter.api;

import com.jsblogs.ratelimiter.enums.HttpMethod;

import java.time.Duration;

public interface IMetadata {

    /**
     * @return API path for which current rate limit is configured.
     */
    String getApiPath();

    /**
     * @return configured HttpMethod
     */
    HttpMethod getMethod();

    /**
     * @return Unique id to identify the client
     */
    String getUniqueId();

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
