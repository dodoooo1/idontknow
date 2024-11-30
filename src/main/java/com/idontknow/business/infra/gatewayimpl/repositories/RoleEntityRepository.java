package com.idontknow.business.infra.gatewayimpl.repositories;

import com.idontknow.business.domain.entities.system.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.ListQuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long>, ListQuerydslPredicateExecutor<RoleEntity> {

}
