package com.idontknow.business.interfaces.system;

import com.idontknow.business.application.services.system.SysRoleService;
import com.idontknow.business.application.services.system.dto.CreateSysRoleRequest;
import com.idontknow.business.application.services.system.dto.UpdateSysRoleRequest;
import com.idontknow.business.constants.AppUrls;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(SysRoleController.BASE_URL)
@RequiredArgsConstructor
@Getter
public class SysRoleController {
    public static final String BASE_URL = AppUrls.ROLE;
    private final SysRoleService service;

    @PostMapping
    public void create(@RequestBody CreateSysRoleRequest request) {
        service.create(request);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable String id, @RequestBody UpdateSysRoleRequest request) {
       service.update(id,request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
