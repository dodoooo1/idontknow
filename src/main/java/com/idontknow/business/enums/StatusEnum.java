package com.idontknow.business.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum StatusEnum {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE");
    private final String name;
}
