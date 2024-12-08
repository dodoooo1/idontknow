package com.idontknow.business.application.dto;

import java.util.List;

/**
 * Represents a set of selectable options with a title, body, available options,
 * and the currently selected option.
 */
public record SelectOptions(String title, String body, List<SelectOption> options, SelectOption selectedOption) {
}
