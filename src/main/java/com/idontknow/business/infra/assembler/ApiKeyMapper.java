package com.idontknow.business.infra.assembler;

import com.idontknow.business.domain.entities.ApiKey;
import com.idontknow.business.infra.assembler.base.ManagementBaseMapper;
import com.idontknow.business.requests.management.CreateApiKeyManagementRequest;
import com.idontknow.business.requests.management.UpdateApiKeyManagementRequest;
import com.idontknow.business.responses.management.ApikeyManagementResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApiKeyMapper
    extends ManagementBaseMapper<
            ApiKey,
                CreateApiKeyManagementRequest,
                UpdateApiKeyManagementRequest,
                ApikeyManagementResponse> {

  @Override
  ApiKey toEntity(CreateApiKeyManagementRequest request);

  @Override
  ApikeyManagementResponse toManagementResponse(ApiKey entity);
}
