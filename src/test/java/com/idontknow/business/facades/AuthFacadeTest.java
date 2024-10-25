package com.idontknow.business.facades;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthFacadeTest {

  public static String API_KEY = "my-apikey-test";
  public static String EMAIL = "test@gmail.com";
  public static String COMPANY_SLUG = "my-company-test";

  @Test
  void verifyGetCompanySlugIsEmptyOnEmptyAuthentication() {
    final var securityContext = Mockito.mock(SecurityContext.class);
    SecurityContextHolder.setContext(securityContext);

  }

  @Test
  void verifyGetCompanySlugIsEmptyOnInvalidAuthentication() {
    final var securityContext = Mockito.mock(SecurityContext.class);
    final var authentication = Mockito.mock(Authentication.class);

    Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
    SecurityContextHolder.setContext(securityContext);
  }

  @Test
  void verifyGetCompanySlugOnJwtAuthentication() {

  }

}
