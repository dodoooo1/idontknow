package com.idontknow.business.mappers;

import com.idontknow.business.entities.Company;
import com.idontknow.business.responses.management.CompanyManagementResponse;
import com.idontknow.business.mappers.base.ManagementBaseMapper;
import com.idontknow.business.requests.management.CreateCompanyManagementRequest;
import com.idontknow.business.requests.management.UpdateCompanyManagementRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper
    extends ManagementBaseMapper<
        Company,
        CreateCompanyManagementRequest,
        UpdateCompanyManagementRequest,
        CompanyManagementResponse> {}
