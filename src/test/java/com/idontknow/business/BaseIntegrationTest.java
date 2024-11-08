package com.idontknow.business;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseIntegrationTest {


    @Autowired
    public MockMvc mockMvc;


    public static String random(final Integer... args) {
        return RandomStringUtils.randomAlphabetic(args.length == 0 ? 10 : args[0]);
    }

    public static String randomNumeric(final Integer... args) {
        return RandomStringUtils.randomNumeric(args.length == 0 ? 10 : args[0]);
    }

}
