package ${packageName}.infra.gatewayimpl.repositories;

import com.idontknow.business.domain.entities.${entityName?lower_case}.${entityName}Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * ${tableComment}仓储接口
 */
@Repository
public interface ${entityName}Repository extends JpaRepository<${entityName}Entity, Long>, QuerydslPredicateExecutor<${entityName}Entity> {
} 