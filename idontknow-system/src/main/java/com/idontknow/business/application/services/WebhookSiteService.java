package com.idontknow.business.application.services;

import com.idontknow.business.core.http.WebhookSiteHttpClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebhookSiteService {

    private final WebhookSiteHttpClient client;

    public Mono<String> post(final Object request) {
        // Deserialization (if needed) is done at services level to keep code DRY.
        return this.client.post(request).map(response -> response);
    }

    public Mono<String> postWithCircuitBreaker(final Object request) {
        return this.client.postWithCircuitBreaker(request).map(response -> response);
    }
}
