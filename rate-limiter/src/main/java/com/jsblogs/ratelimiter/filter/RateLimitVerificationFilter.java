package com.jsblogs.ratelimiter.filter;

import com.jsblogs.ratelimiter.api.IMetadata;
import com.jsblogs.ratelimiter.api.IRequest;
import com.jsblogs.ratelimiter.api.IRequestMatcher;
import com.jsblogs.ratelimiter.api.internal.HttpRequest;
import com.jsblogs.ratelimiter.config.RateLimitConfig;
import com.jsblogs.ratelimiter.utils.ApplicationUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.jsblogs.ratelimiter.utils.Constants.APPLICATION_JSON;
import static com.jsblogs.ratelimiter.utils.Constants.TOO_MANY_REQUESTS;

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
        IRequestMatcher.MatchedResult matchedResult = rateLimitConfig.getRequestMatcher().matches(req);
        if (matchedResult.isMatched()) {
            // Request matches to the RateLimiter
            // Perform rate limiting logic here
            IMetadata metadata = matchedResult.getMatchedMetadata();
            if (metadata.getAllowedApiLimit() > 0) {
                // limit allowed
                rateLimitConfig.getStore().markVisited(req);
            } else {
                writeRateLimitExpiredMessage(response, metadata);
            }
        }
        chain.doFilter(request, response);
    }

    private void writeRateLimitExpiredMessage(ServletResponse resp, IMetadata metadata) throws IOException {
        HttpServletResponse response = (HttpServletResponse) resp;
        PrintWriter writer = response.getWriter();
        response.setContentType(APPLICATION_JSON);
        response.setStatus(TOO_MANY_REQUESTS);
        writer.write(ApplicationUtils.getErrorResponse(
                String.format("Rate limit exceeded. Next window in %s seconds.", metadata.getRemainingTime())
        ));
    }

    @Override
    public void destroy() {

    }

    protected IRequest convertRequest(HttpServletRequest servletRequest) {
        return new HttpRequest(servletRequest, rateLimitConfig.getIdFetcher());
    }
}
