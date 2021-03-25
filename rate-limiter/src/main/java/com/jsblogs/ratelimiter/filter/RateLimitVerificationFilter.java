package com.jsblogs.ratelimiter.filter;

import com.jsblogs.ratelimiter.config.RateLimitConfig;

import javax.servlet.*;
import java.io.IOException;

/**
 * RateLimit Verification Filter takes care the limit available
 */
public class RateLimitVerificationFilter implements Filter {

    private final RateLimitConfig rateLimitConfig;

    public RateLimitVerificationFilter(RateLimitConfig rateLimitConfig) {
        this.rateLimitConfig = rateLimitConfig;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }
}
