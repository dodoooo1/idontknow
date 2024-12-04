package com.idontknow.business.application.services.system.query;

import com.idontknow.business.application.dto.TimeRange;
import com.idontknow.business.enums.StatusEnum;

/**
 * Represents a filter for searching users based on specific date and time ranges.
 * <p>
 * This class accepts a SearchUsersDateTimeFilter object as a parameter for filtering users based on creation, update, or closure time ranges.
 */
public record SearchUsersFilter(
        String name,
        String username,
        String email,
        StatusEnum status,
        TimeRange createdAt,
        TimeRange updatedAt
) {
}
