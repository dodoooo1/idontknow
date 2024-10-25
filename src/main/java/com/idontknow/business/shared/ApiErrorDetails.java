package com.idontknow.business.shared;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record ApiErrorDetails(@JsonInclude(Include.NON_NULL) String pointer, String reason)
    implements Serializable {}
