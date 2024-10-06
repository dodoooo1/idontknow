package com.idontknow.business.testutils.builders;

import com.idontknow.business.BaseIntegrationTest;
import com.idontknow.business.entities.ApiKey;
import com.idontknow.business.entities.Company;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ApiKeyBuilder {
  public static ApiKey apiKey(final Company company) {
    return ApiKey.builder().name(BaseIntegrationTest.random()).companyId(company.getId()).build();
  }
}
