package com.idontknow.business.application.services.system.dto;

public record CreateDictionaryEntryRequest(
        Long dictId,
        String itemValue,
        String itemLabel,
        Integer sort,
        String status
) {

}
