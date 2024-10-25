

package com.idontknow.business.domain.ability;

import com.idontknow.business.domain.entities.system.SysUserEntity;
import com.idontknow.business.domain.gateway.SysUserGateway;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 */
@Getter
@Component
@RequiredArgsConstructor
public class SysUserDomainService {

	private final SysUserGateway sysUserGateway;

	public void create(SysUserEntity sysUserEntity) {
		sysUserGateway.create(sysUserEntity);
	}

	public void update(SysUserEntity sysUserEntity) {
		sysUserGateway.update(sysUserEntity);
	}

	public void delete(Long[] ids) {
		sysUserGateway.delete(ids);
	}

}
