
package com.idontknow.business.domain.gateway;

import com.idontknow.business.domain.entities.system.SysUserEntity;

/**
 * 系统用户网关
 */
public interface SysUserGateway {

	// 检查用户名是否存在
	public boolean isUsernameTaken(String username) ;

	// 检查邮箱是否存在
	public boolean isEmailTaken(String email) ;

	public void create(SysUserEntity sysUserEntity) ;

	public void update(SysUserEntity sysUserEntity) ;

	public void delete(Long[] ids);

}
