package com.idontknow.business.testutils.builders;

import com.idontknow.business.BaseIntegrationTest;
import com.idontknow.business.domain.entities.Company;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CompanyBuilder {

  public static Company company() {
    return Company.builder().name(BaseIntegrationTest.random()).slug(BaseIntegrationTest.random()).build();
  }

  public static Company platform() {
    return Company.builder().name(BaseIntegrationTest.random()).slug(BaseIntegrationTest.random()).isPlatform(true).build();
  }

  public static Company management() {
    return Company.builder().name(BaseIntegrationTest.random()).slug(BaseIntegrationTest.random()).isManagement(true).build();
  }

  public static Company internal() {
    return Company.builder().name(BaseIntegrationTest.random()).slug(BaseIntegrationTest.random()).isInternal(true).build();
  }
}
