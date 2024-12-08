package com.idontknow.business.infra.assembler;


import com.idontknow.business.application.services.system.dto.CreateDictionariesRequest;
import com.idontknow.business.application.services.system.dto.DictionariesResponse;
import com.idontknow.business.application.services.system.dto.UpdateDictionariesRequest;
import com.idontknow.business.domain.entities.system.DictionaryEntity;
import com.idontknow.business.infra.assembler.base.SystemBaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DictionariesMapper
        extends SystemBaseMapper<
        DictionaryEntity,
        CreateDictionariesRequest,
        UpdateDictionariesRequest,
        DictionariesResponse> {
    @Override
    DictionaryEntity toEntity(CreateDictionariesRequest request);

    @Override
    DictionariesResponse toSystemResponse(DictionaryEntity entity);

}
