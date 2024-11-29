package com.idontknow.business.domain.ability;

import com.idontknow.business.application.services.system.dto.UpdateSysUserRequest;
import com.idontknow.business.domain.entities.system.SysUser;
import com.idontknow.business.domain.gateway.SysUserGateway;
import com.idontknow.business.infra.assembler.SysUserMapper;
import com.idontknow.business.infra.configs.security.auth.providers.JwtAuthenticationToken;
import com.idontknow.business.infra.configs.security.auth.providers.SecurityUtils;
import com.idontknow.business.infra.gatewayimpl.dataobject.system.SysUserDO;
import com.idontknow.business.infra.gatewayimpl.repositories.SysUserRepository;
import com.idontknow.business.infra.gatewayimpl.repositories.SysUserRoleRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 *
 */
@Slf4j
@Getter
@Service
@RequiredArgsConstructor
public class SysUserDomainService {

    private final SysUserGateway gateway;
    private final SysUserMapper mapper;
    private final SysUserRoleRepository sysUserRoleRepository;
    private final SysUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public void create(SysUser sysUser) {
        sysUser.updatePassword(passwordEncoder.encode(sysUser.getPassword()));
        SysUserDO sysUserDO = mapper.toPersist(sysUser);
        JwtAuthenticationToken currentUser = SecurityUtils.getCurrentUser().get();
        repository.save(sysUserDO);
    }

    //更新状态，启用、禁用
    public void updateStatus(UpdateSysUserRequest request) {
        SysUserDO sysUserDO = repository.findById(Long.valueOf(request.id())).orElseThrow(() -> new RuntimeException("User not exists!"));
        sysUserDO.setPassword(request.status());
        repository.save(sysUserDO);
    }

    /**
     * Update the password for a specific user.
     *
     * @param request The request containing the user ID and the new password
     */
    public void updatePassword(UpdateSysUserRequest request) {
        SysUserDO sysUserDO = repository.findById(Long.valueOf(request.id())).orElseThrow(() -> new RuntimeException("User not exists!"));
        if (!matchesPassword(sysUserDO.getPassword(), request.password())) {
            throw new RuntimeException("New password cannot be the same as the old password");
        }
        String newPassword = passwordEncoder.encode(request.password());
        sysUserDO.setPassword(newPassword);
        repository.save(sysUserDO);
    }

    public void update(SysUser sysUser) {
        gateway.update(sysUser);
    }

    public void delete(Long[] ids) {
        gateway.delete(ids);
    }

    public Optional<SysUserDO> loadUserByUsername(String username) {
        return gateway.loadUserByUsername(username);
    }

    public SysUserDO findById(String id) {
        return gateway.findById(Long.valueOf(id));
    }

    public void deleteUserRoleAssociation(Long userId) {
        sysUserRoleRepository.deleteUserRoleAssociation(userId);
    }

    public boolean matchesPassword(String loginPassword, String password) {
        return passwordEncoder.matches(loginPassword, password);
    }
}
