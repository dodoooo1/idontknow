package com.idontknow.business.application.dto;

/**
 * Represents a selectable option with a reference ID and a title.
 *
 * @param referenceId The reference ID of the option.
 * @param title       The title of the option.
 */
public record SelectOption(String referenceId, String title) {
}
