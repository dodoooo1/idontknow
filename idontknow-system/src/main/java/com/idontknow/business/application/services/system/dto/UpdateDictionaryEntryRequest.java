package com.idontknow.business.application.services.system.dto;

public record UpdateDictionaryEntryRequest(Long id, Long dictId, String itemValue, String itemLabel, Integer sort,
                                           String status) {

}
