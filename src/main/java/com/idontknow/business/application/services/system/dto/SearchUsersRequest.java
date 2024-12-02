package com.idontknow.business.application.services.system.dto;

/**
 * This is a model class for SearchOrdersRequest type.
 */
public record SearchUsersRequest(
        SearchUsersFilter filter,
        SearchUsersSort sort,
        int number,
        int size) {
}
