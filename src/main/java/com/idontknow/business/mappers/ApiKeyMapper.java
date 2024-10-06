package com.idontknow.business.mappers;

import com.idontknow.business.entities.ApiKey;
import com.idontknow.business.responses.management.ApikeyManagementResponse;
import com.idontknow.business.mappers.base.ManagementBaseMapper;
import com.idontknow.business.requests.management.CreateApiKeyManagementRequest;
import com.idontknow.business.requests.management.UpdateApiKeyManagementRequest;
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
