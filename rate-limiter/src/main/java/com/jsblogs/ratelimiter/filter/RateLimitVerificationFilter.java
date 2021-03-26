package com.jsblogs.ratelimiter.filter;

import com.jsblogs.ratelimiter.api.IMetadata;
import com.jsblogs.ratelimiter.api.IRequest;
import com.jsblogs.ratelimiter.api.internal.HttpRequest;
import com.jsblogs.ratelimiter.config.RateLimitConfig;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
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
        IRequest req = convertRequest((HttpServletRequest) request);
        if (rateLimitConfig.getRequestMatcher().matches(req)) {
            // Request matches to the RateLimiter
            // Perform rate limiting logic here
            IMetadata metadata = rateLimitConfig.getStore().getRateLimitMetaData(req);
            if (metadata.getAllowedApiLimit() > 0) {
                // limit allowed
                rateLimitConfig.getStore().markVisited(req);
            } else {
                writeRateLimitExpiredMessage(response, metadata);
            }
        }
        chain.doFilter(request, response);
    }

    private void writeRateLimitExpiredMessage(ServletResponse response, IMetadata metadata) {
        
    }

    @Override
    public void destroy() {

    }

    protected IRequest convertRequest(HttpServletRequest servletRequest) {
        return new HttpRequest(servletRequest, rateLimitConfig.getIdFetcher());
    }
}
