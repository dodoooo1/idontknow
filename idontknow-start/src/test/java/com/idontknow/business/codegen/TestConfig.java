package com.idontknow.business.codegen;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.idontknow.business")
@PropertySource("classpath:application-dev.yml")
public class TestConfig {
}
