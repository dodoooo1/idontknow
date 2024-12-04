package com.idontknow.business.infra.assembler;


import com.idontknow.business.application.services.system.dto.CreateOperateLogRequest;
import com.idontknow.business.application.services.system.dto.OperateLogResponse;
import com.idontknow.business.application.services.system.dto.UpdateOperateLogRequest;
import com.idontknow.business.domain.entities.system.OperateLogEntity;
import com.idontknow.business.infra.assembler.base.SystemBaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OperateLogMapper
        extends SystemBaseMapper<
        OperateLogEntity,
        CreateOperateLogRequest,
        UpdateOperateLogRequest,
        OperateLogResponse> {
    @Override
    OperateLogEntity toEntity(CreateOperateLogRequest request);

    @Override
    OperateLogResponse toSystemResponse(OperateLogEntity entity);

}
