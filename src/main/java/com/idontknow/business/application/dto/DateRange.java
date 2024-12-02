package com.idontknow.business.application.dto;

/**
 * Represents a date range with a start date and an end date.
 *
 * @param startDate the start date of the range
 * @param endDate   the end date of the range
 */
public record DateRange(String startDate, String endDate) {
}
