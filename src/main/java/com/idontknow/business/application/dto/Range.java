package com.idontknow.business.application.dto;

/**
 * Represents a range with minimum and maximum values. The minimum and maximum values are of type String.
 *
 * @param min the minimum value of the range
 * @param max the maximum value of the range
 */
public record Range(String min, String max) {
}
