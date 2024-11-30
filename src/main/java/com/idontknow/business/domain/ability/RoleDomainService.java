package com.idontknow.business.domain.ability;

import com.idontknow.business.domain.ability.base.BaseService;
import com.idontknow.business.domain.entities.system.RoleEntity;
import com.idontknow.business.infra.gatewayimpl.repositories.RoleEntityRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Getter
@Service
@RequiredArgsConstructor
public class RoleDomainService extends BaseService<RoleEntity> {

    private final RoleEntityRepository repository;


    public RoleEntity create(RoleEntity entity) {
        entity.setCreatedBy("admin");
        entity.setUpdatedBy("admin");
        return repository.save(entity);
    }

    public RoleEntity update(RoleEntity roleEntity) {
        return repository.save(roleEntity);
    }

    public void delete(List<Long> ids) {
        repository.deleteAllById(ids);
    }

}
