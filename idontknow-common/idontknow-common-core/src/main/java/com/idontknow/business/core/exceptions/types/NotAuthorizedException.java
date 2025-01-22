package com.idontknow.business.core.exceptions.types;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@ResponseStatus(code = UNAUTHORIZED)
public class NotAuthorizedException extends RootException {

  @Serial private static final long serialVersionUID = -711441617476620028L;

  public NotAuthorizedException() {
    super(UNAUTHORIZED);
  }
}
