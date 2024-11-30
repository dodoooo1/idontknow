package com.idontknow.business.adapter.system;

import com.idontknow.business.application.services.system.RoleEntityService;
import com.idontknow.business.application.services.system.dto.CreateEntityRoleRequest;
import com.idontknow.business.application.services.system.dto.UpdateRoleEntityRequest;
import com.idontknow.business.constants.AppUrls;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(RoleEntityController.BASE_URL)
@RequiredArgsConstructor
@Getter
public class RoleEntityController {
    public static final String BASE_URL = AppUrls.ROLE;
    private final RoleEntityService service;

    @PostMapping
    public void create(@RequestBody CreateEntityRoleRequest request) {
        service.create(request);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable String id, @RequestBody UpdateRoleEntityRequest request) {
        service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
