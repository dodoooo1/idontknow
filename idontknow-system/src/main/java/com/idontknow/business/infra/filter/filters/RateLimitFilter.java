/*
package com.idontknow.business.infra.filter.filters;

import com.idontknow.business.infra.ratelimit.DefaultRateLimit;
import com.idontknow.business.infra.ratelimit.base.BaseRateLimit;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.core.convert.Bucket;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.springframework.http.HttpStatus.TOO_MANY_REQUESTS;

@Slf4j
@Component
@RequiredArgsConstructor
public class RateLimitFilter extends OncePerRequestFilter {

    public static final String HEADER_RATE_LIMIT_REMAINING = "X-Rate-Limit-Remaining";
    public static final String HEADER_RATE_LIMIT_RETRY_AFTER_SECONDS =
            "X-Rate-Limit-Retry-After-Milliseconds";

    private final DefaultRateLimit defaultRateLimit;
    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    @Override
    protected void doFilterInternal(
            @NonNull final HttpServletRequest request,
            @NonNull final HttpServletResponse response,
            @NonNull final FilterChain filterChain)
            throws ServletException, IOException {

        final Bucket bucket = this.resolveBucket(request);
        final ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);

        if (probe.isConsumed()) {
            // Comment if you want to hide remaining request.
            response.addHeader(HEADER_RATE_LIMIT_REMAINING, String.valueOf(probe.getRemainingTokens()));
            filterChain.doFilter(request, response);
        } else {

            final long waitForRefill = probe.getNanosToWaitForRefill() / 1_000_000;

            response.reset();
            // Comment if you want to hide remaining time before refill.
            response.addHeader(HEADER_RATE_LIMIT_RETRY_AFTER_SECONDS, String.valueOf(waitForRefill));
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(TOO_MANY_REQUESTS.value());
        }
    }

    private Bucket resolveBucket(final HttpServletRequest request) {
        final BaseRateLimit rateLimit = this.getRateLimitFor(request.getRequestURI());
        return this.cache.computeIfAbsent(
                // Rate limit bucket on remote address = IP address
                request.getRemoteAddr(), s -> Bucket.builder().addLimit(rateLimit.getLimit()).build());
    }

    private BaseRateLimit getRateLimitFor(final String requestedUri) {
        // Use a switch case if you want to rate limit multiple URL.
        return this.defaultRateLimit;
    }
}
*/
