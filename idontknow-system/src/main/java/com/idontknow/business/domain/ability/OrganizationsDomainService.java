package com.idontknow.business.domain.ability;

import com.idontknow.business.domain.ability.base.BaseDomainService;
import com.idontknow.business.domain.entities.system.OrganizationEntity;
import com.idontknow.business.infra.assembler.OrganizationsMapper;
import com.idontknow.business.infra.configs.security.auth.providers.JwtAuthenticationToken;
import com.idontknow.business.infra.configs.security.auth.providers.SecurityUtils;
import com.idontknow.business.infra.gatewayimpl.repositories.OrganizationsRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *
 */
@Slf4j
@Getter
@Service
@RequiredArgsConstructor
public class OrganizationsDomainService extends BaseDomainService<OrganizationEntity> {

    private final OrganizationsMapper mapper;
    private final OrganizationsRepository repository;

    public OrganizationEntity create(OrganizationEntity entity) {
        JwtAuthenticationToken currentUser = SecurityUtils.getCurrentUser().get();
        entity.setCreatedBy(currentUser.getUserInfo().getUsername());
        entity.setUpdatedBy(currentUser.getUserInfo().getUsername());
        return repository.save(entity);
    }

    public OrganizationEntity update(OrganizationEntity entity) {
        return repository.save(entity);
    }

}
