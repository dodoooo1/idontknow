package com.idontknow.business.infra.gatewayimpl.repositories;

import com.idontknow.business.infra.gatewayimpl.dataobject.system.SysUserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserRepository extends JpaRepository<SysUserDO, Long>, QuerydslPredicateExecutor<SysUserDO> {

}
