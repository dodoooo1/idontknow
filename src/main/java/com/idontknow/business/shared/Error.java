package com.idontknow.business.shared;

/**
 * This is a model class for Error type.
 */
public record Error(String category, String code, String detail, String field) {
}
