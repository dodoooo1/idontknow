package com.idontknow.business.interfaces.management;

import com.idontknow.business.application.services.CompanyService;
import com.idontknow.business.constants.AppUrls;
import com.idontknow.business.domain.entities.Company;
import com.idontknow.business.infra.assembler.CompanyMapper;
import com.idontknow.business.interfaces.management.base.BaseManagementController;
import com.idontknow.business.requests.management.CreateCompanyManagementRequest;
import com.idontknow.business.requests.management.UpdateCompanyManagementRequest;
import com.idontknow.business.responses.management.CompanyManagementResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(CompanyManagementController.BASE_URL)
@RequiredArgsConstructor
public class CompanyManagementController
    extends BaseManagementController<
            Company,
                CreateCompanyManagementRequest,
                UpdateCompanyManagementRequest,
                CompanyManagementResponse> {

  public static final String BASE_URL = AppUrls.MANAGEMENT + "/companies";

  @Getter private final CompanyService service;
  @Getter private final CompanyMapper mapper;
}
