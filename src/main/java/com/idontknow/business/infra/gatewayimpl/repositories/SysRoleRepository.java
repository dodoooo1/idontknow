package com.idontknow.business.infra.gatewayimpl.repositories;

import com.idontknow.business.infra.gatewayimpl.dataobject.system.SysRoleDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRoleRepository extends JpaRepository<SysRoleDO, Long>, QuerydslPredicateExecutor<SysRoleDO> {

}
