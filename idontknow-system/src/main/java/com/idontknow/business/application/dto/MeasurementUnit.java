package com.idontknow.business.application.dto;

/**
 * A record representing a set of measurement units including custom unit, area unit, length unit, volume unit,
 * weight unit, generic unit, time unit, and type.
 *
 * @param customUnit  The custom unit of measurement.
 * @param areaUnit    The unit used for area measurements.
 * @param lengthUnit  The unit used for length measurements.
 * @param volumeUnit  The unit used for volume measurements.
 * @param weightUnit  The unit used for weight measurements.
 * @param genericUnit The generic unit of measurement.
 * @param timeUnit    The unit used for time measurements.
 * @param type        The type of the measurement units.
 */
public record MeasurementUnit(
        MeasurementUnitCustom customUnit,
        String areaUnit,
        String lengthUnit,
        String volumeUnit,
        String weightUnit,
        String genericUnit,
        String timeUnit,
        String type) {
}
