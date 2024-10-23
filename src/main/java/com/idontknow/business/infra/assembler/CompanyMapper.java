package com.idontknow.business.infra.assembler;

import com.idontknow.business.domain.entities.Company;
import com.idontknow.business.infra.assembler.base.ManagementBaseMapper;
import com.idontknow.business.requests.management.CreateCompanyManagementRequest;
import com.idontknow.business.requests.management.UpdateCompanyManagementRequest;
import com.idontknow.business.responses.management.CompanyManagementResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper
    extends ManagementBaseMapper<
            Company,
                CreateCompanyManagementRequest,
                UpdateCompanyManagementRequest,
                CompanyManagementResponse> {}
