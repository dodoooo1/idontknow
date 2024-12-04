package com.idontknow.business.domain.ability;

import com.idontknow.business.domain.ability.base.BaseDomainService;
import com.idontknow.business.domain.entities.system.RoleEntity;
import com.idontknow.business.infra.assembler.RolesMapper;
import com.idontknow.business.infra.configs.security.auth.providers.JwtAuthenticationToken;
import com.idontknow.business.infra.configs.security.auth.providers.SecurityUtils;
import com.idontknow.business.infra.gatewayimpl.repositories.RolesRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

/**
 *
 */
@Getter
@Service
@RequiredArgsConstructor
public class RolesDomainService extends BaseDomainService<RoleEntity> {
    private final RolesMapper mapper;
    private final RolesRepository repository;


    public RoleEntity create(RoleEntity entity) {

        JwtAuthenticationToken currentUser = SecurityUtils.getCurrentUser().get();
        Assert.notNull(currentUser, "currentUser is null");
        entity.setCreatedBy(currentUser.getUserInfo().getUsername());
        entity.setUpdatedBy(currentUser.getUserInfo().getUsername());
        return super.create(entity);
    }

    public RoleEntity update(RoleEntity entity) {
        JwtAuthenticationToken currentUser = SecurityUtils.getCurrentUser().get();
        entity.setUpdatedBy(currentUser.getUserInfo().getUsername());
        entity.setUpdatedAt(LocalDateTime.now());
        return super.update(entity);
    }


}
