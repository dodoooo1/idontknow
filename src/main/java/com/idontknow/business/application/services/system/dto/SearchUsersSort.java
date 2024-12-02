package com.idontknow.business.application.services.system.dto;

/**
 * Represents the sorting criteria for searching users.
 * <p>
 * This class defines the sorting field and order for searching users.
 *
 * @param sortField The field to sort the search results by.
 * @param sortOrder The order in which the search results should be sorted (ascending or descending).
 */
public record SearchUsersSort(
        String sortField,
        String sortOrder) {
}
