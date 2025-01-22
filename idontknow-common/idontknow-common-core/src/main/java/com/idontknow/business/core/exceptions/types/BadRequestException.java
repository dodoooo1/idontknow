package com.idontknow.business.core.exceptions.types;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(code = BAD_REQUEST)
public class BadRequestException extends RootException {

  @Serial private static final long serialVersionUID = -4391321810681152889L;

  public BadRequestException(final String message) {
    super(BAD_REQUEST, message);
  }
}
