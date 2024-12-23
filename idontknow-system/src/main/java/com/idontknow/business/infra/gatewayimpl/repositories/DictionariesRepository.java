package com.idontknow.business.infra.gatewayimpl.repositories;

import com.idontknow.business.domain.entities.system.DictionaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.ListQuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @title: DictionariesRepository
 * @package com.idontknow.business.infra.gatewayimpl.repositories
 * @author: glory
 * @date: 2024/12/2 21:22
 */
@Repository
public interface DictionariesRepository extends JpaRepository<DictionaryEntity, Long>, ListQuerydslPredicateExecutor<DictionaryEntity> {
}
