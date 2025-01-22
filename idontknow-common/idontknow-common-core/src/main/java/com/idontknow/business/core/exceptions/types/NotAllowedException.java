package com.idontknow.business.core.exceptions.types;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

import static org.springframework.http.HttpStatus.FORBIDDEN;

@ResponseStatus(code = FORBIDDEN)
public class NotAllowedException extends RootException {

  @Serial private static final long serialVersionUID = -1774388035625344059L;

  public NotAllowedException() {
    super(FORBIDDEN);
  }
}
