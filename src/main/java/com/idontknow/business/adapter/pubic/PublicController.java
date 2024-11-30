package com.idontknow.business.adapter.pubic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idontknow.business.application.services.WebhookSiteService;
import com.idontknow.business.application.services.system.dto.SysUserResponse;
import com.idontknow.business.constants.AppUrls;
import com.idontknow.business.domain.entities.system.UserEntity;
import com.idontknow.business.infra.event.publishers.EventPublisher;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping(PublicController.BASE_URL)
@RequiredArgsConstructor
public class PublicController {
    public static final String BASE_URL = AppUrls.PUBLIC;

    private final EventPublisher eventPublisher;
    private final WebhookSiteService webhookSiteService;

    @Value("${rabbitmq.publishers.webhook.exchange}")
    private String exchange;

    @Value("${rabbitmq.publishers.webhook.routingkey}")
    private String routingKey;

    @GetMapping("/hello-world")
    @ResponseStatus(HttpStatus.OK)
    public String helloWorld() {

        return "Hello world";
    }

    @GetMapping("/publish")
    @ResponseStatus(HttpStatus.OK)
    public String publish() {
        this.eventPublisher.publish(this.exchange, this.routingKey, Map.of("test", "test"));
        return "published";
    }

    @GetMapping("/call-external-api")
    @ResponseStatus(HttpStatus.OK)
    public Mono<String> callExternalAPI() {
        return this.webhookSiteService.post(Map.of());
    }

    @GetMapping("/call-external-api-with-cb")
    @ResponseStatus(HttpStatus.OK)
    public Mono<String> callExternalAPIWithCircuitBreaker() {
        return this.webhookSiteService.postWithCircuitBreaker(Map.of());
    }


    @PostMapping("/signup")
    public ResponseEntity<SysUserResponse> register(@Valid @RequestBody UserEntity entity) {
        try {
       /* System.out.println(new ObjectMapper().writeValueAsString(createSysUserRequest));
        SysUserEntity entity = mapper.toEntity(createSysUserRequest);*/
            System.out.println(new ObjectMapper().writeValueAsString(entity));
            //  authenticationService.signup(entity);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok().build();
    }
}
