package com.idontknow.business.infra.gatewayimpl.repositories;

import com.idontknow.business.domain.entities.system.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.ListQuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationsRepository extends JpaRepository<OrganizationEntity, Long>, ListQuerydslPredicateExecutor<OrganizationEntity> {

}
