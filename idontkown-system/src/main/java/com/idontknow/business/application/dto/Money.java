package com.idontknow.business.application.dto;


import java.math.BigDecimal;

/**
 * Represents a monetary amount with its corresponding currency.
 * <p>
 * The Money class provides a simple immutable data structure to store a monetary amount
 * along with its currency. Instances of this class are created using the provided constructor.
 *
 * @param amount   The amount of money represented as a BigDecimal. Must not be null.
 * @param currency The currency of the money as a String. Must not be null.
 */
public record Money(BigDecimal amount, String currency) {
}
