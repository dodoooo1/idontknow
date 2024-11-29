package com.idontknow.business.infra.gatewayimpl.repositories;

import com.idontknow.business.infra.gatewayimpl.dataobject.system.SysUserRoleDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserRoleRepository extends JpaRepository<SysUserRoleDO, Long> {

    @Modifying
    @Query("DELETE FROM SysUserRoleDO ur WHERE ur.userId = :userId")
    void deleteUserRoleAssociation(@Param("userId") Long userId);
}
