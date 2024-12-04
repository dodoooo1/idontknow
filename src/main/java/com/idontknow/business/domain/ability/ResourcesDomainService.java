package com.idontknow.business.domain.ability;

import com.idontknow.business.domain.ability.base.BaseDomainService;
import com.idontknow.business.domain.entities.system.ResourceEntity;
import com.idontknow.business.infra.assembler.base.ResourcesMapper;
import com.idontknow.business.infra.configs.security.auth.providers.JwtAuthenticationToken;
import com.idontknow.business.infra.configs.security.auth.providers.SecurityUtils;
import com.idontknow.business.infra.gatewayimpl.repositories.ResourcesRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 *
 */
@Slf4j
@Getter
@Service
@RequiredArgsConstructor
public class ResourcesDomainService extends BaseDomainService<ResourceEntity> {

    private final ResourcesMapper mapper;
    private final ResourcesRepository repository;

    public ResourceEntity create(ResourceEntity entity) {
        JwtAuthenticationToken currentUser = SecurityUtils.getCurrentUser().get();
        Assert.notNull(currentUser, "currentUser is null");
        entity.setCreatedBy(currentUser.getUserInfo().getUsername());
        entity.setUpdatedBy(currentUser.getUserInfo().getUsername());
        return repository.save(entity);
    }


    public ResourceEntity update(ResourceEntity entity) {
        return repository.save(entity);
    }

}
