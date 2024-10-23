package com.idontknow.business.interfaces.management;

import com.idontknow.business.application.services.ApiKeyService;
import com.idontknow.business.constants.AppUrls;
import com.idontknow.business.domain.entities.ApiKey;
import com.idontknow.business.infra.assembler.ApiKeyMapper;
import com.idontknow.business.interfaces.management.base.BaseManagementController;
import com.idontknow.business.requests.management.CreateApiKeyManagementRequest;
import com.idontknow.business.requests.management.UpdateApiKeyManagementRequest;
import com.idontknow.business.responses.management.ApikeyManagementResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(ApikeyManagementController.BASE_URL)
@RequiredArgsConstructor
public class ApikeyManagementController
    extends BaseManagementController<
            ApiKey,
                CreateApiKeyManagementRequest,
                UpdateApiKeyManagementRequest,
                ApikeyManagementResponse> {
  public static final String BASE_URL = AppUrls.MANAGEMENT + "/api-keys";

  @Getter private final ApiKeyService service;
  @Getter private final ApiKeyMapper mapper;

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  @Override
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public void delete(@PathVariable("id") final Long id) {
    log.info("[request] inactive {} '{}'", ApiKey.TABLE_NAME, id);
    this.service.inactivate(id);
  }
}
