package com.idontknow.business.application.services;

import com.idontknow.business.application.services.base.BaseService;
import com.idontknow.business.entities.Company;
import com.idontknow.business.exceptions.ResourceNotFoundException;
import com.idontknow.business.infra.repositories.CompanyRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class CompanyService extends BaseService<Company> {
  @Getter private final CompanyRepository repository;

  public Optional<Company> findBySlugOptional(final String slug) {
    log.debug("[retrieving] company with slug '{}'", slug);
    if (StringUtils.isBlank(slug)) {
      return Optional.empty();
    }
    return this.repository.findBySlug(slug);
  }

  public Company findBySlug(final String slug) {
    return this.findBySlugOptional(slug)
        .orElseThrow(
            () ->
                new ResourceNotFoundException(
                    String.format("company with slug '%s' not found", slug)));
  }
}
