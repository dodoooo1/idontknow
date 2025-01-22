package ${packageName}.infra.assembler;

import com.idontknow.business.application.services.${entityName?lower_case}.dto.Create${entityName}Request;
import com.idontknow.business.application.services.${entityName?lower_case}.dto.Update${entityName}Request;
import com.idontknow.business.application.services.${entityName?lower_case}.dto.${entityName}Response;
import com.idontknow.business.domain.entities.${entityName?lower_case}.${entityName}Entity;
import org.mapstruct.*;

/**
 * ${tableComment} Mapper
 */
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ${entityName}Mapper {

    ${entityName}Entity toEntity(Create${entityName}Request request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ${entityName}Entity update(Update${entityName}Request request, @MappingTarget ${entityName}Entity entity);

    ${entityName}Response toSystemResponse(${entityName}Entity entity);
}