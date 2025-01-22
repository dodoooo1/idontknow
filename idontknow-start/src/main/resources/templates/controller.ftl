package ${packageName}.adapter;

import com.idontknow.business.adapter.base.BaseSystemController;
import com.idontknow.business.application.services.${entityName?lower_case}.${entityName}Service;
import com.idontknow.business.application.services.${entityName?lower_case}.dto.Create${entityName}Request;
import com.idontknow.business.application.services.${entityName?lower_case}.dto.Update${entityName}Request;
import com.idontknow.business.application.services.${entityName?lower_case}.dto.${entityName}Response;
import com.idontknow.business.application.services.${entityName?lower_case}.query.Search${entityName}Request;
import com.idontknow.business.core.constants.AppUrls;
import com.idontknow.business.domain.entities.${entityName?lower_case}.${entityName}Entity;
import com.idontknow.business.infra.assembler.${entityName}Mapper;
import com.idontknow.business.jpa.ApiListPaginationSuccess;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * ${tableComment}控制器
 */
@Slf4j
@RestController
@RequestMapping(${entityName}Controller.BASE_URL)
@RequiredArgsConstructor
@Getter
public class ${entityName}Controller extends BaseSystemController<
        ${entityName}Entity,
        Create${entityName}Request,
        Update${entityName}Request,
        ${entityName}Response> {

    public static final String BASE_URL = AppUrls.${entityName?upper_case};
    private final ${entityName}Service service;
    private final ${entityName}Mapper mapper;

    @GetMapping
    public ApiListPaginationSuccess<${entityName}Response> get${entityName}s(@RequestBody final Search${entityName}Request request) {
        return service.get${entityName}s(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<${entityName}Response> create(@Valid @RequestBody final Create${entityName}Request request) {
        log.info("[request] create {}", request);
        final ${entityName}Entity entity = service.create(mapper.toEntity(request));
        return ResponseEntity.ok(mapper.toSystemResponse(entity));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public ResponseEntity<${entityName}Response> update(
            @PathVariable("id") final Long id,
            @Valid @RequestBody final Update${entityName}Request request) {
        log.info("[request] update '{}' {}", id, request);
        final ${entityName}Entity entity = service.findById(id);
        Assert.notNull(entity, "${entityName} not found");
        return ResponseEntity.ok(mapper.toSystemResponse(service.update(mapper.update(request, entity))));
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public ResponseEntity<${entityName}Response> patch(
            @PathVariable("id") final Long id,
            @RequestBody final Update${entityName}Request request) {
        log.info("[request] patch '{}' {}", id, request);
        final ${entityName}Entity entity = service.findById(id);
        Assert.notNull(entity, "${entityName} not found");
        Assert.isTrue(request.version() != entity.getVersion(), "Version mismatch");
        return ResponseEntity.ok(mapper.toSystemResponse(service.update(mapper.update(request, entity))));
    }
}
