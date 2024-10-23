package com.idontknow.business;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.annotation.PostConstruct;
import java.util.TimeZone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@Slf4j
@EnableAsync
@EnableCaching
@ConfigurationPropertiesScan
@ServletComponentScan
@SpringBootApplication
@SecurityScheme(
        name = "Keycloak"
        , openIdConnectUrl = "http://localhost:8080/realms/api/.well-known/openid-configuration"
        , scheme = "bearer"
        , type = SecuritySchemeType.OPENIDCONNECT
        , in = SecuritySchemeIn.HEADER
)
public class ApiApplication {

  // static {
  //	BlockHound.install();
  // }

  public static void main(final String[] args) {

    final Runtime r = Runtime.getRuntime();

    log.info("[APP] Active processors: {}", r.availableProcessors());
    log.info("[APP] Total memory: {}", r.totalMemory());
    log.info("[APP] Free memory: {}", r.freeMemory());
    log.info("[APP] Max memory: {}", r.maxMemory());

    SpringApplication.run(ApiApplication.class, args);
  }

  @PostConstruct
  void started() {
    TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
  }
}
