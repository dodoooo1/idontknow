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

import java.util.Optional;

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
        Optional<JwtAuthenticationToken> jwtAuthenticationToken = SecurityUtils.getCurrentUser();
        jwtAuthenticationToken.orElseThrow(() -> new RuntimeException("currentUser is null！"));
        JwtAuthenticationToken currentUser = jwtAuthenticationToken.get();
        entity.setOrganizationId(currentUser.getUserInfo().getOrganization().getId());

        return super.create(entity);
    }

    public RoleEntity update(RoleEntity entity) {
        return super.update(entity);
    }


}
