package com.idontknow.business.utils;

import lombok.experimental.UtilityClass;

import java.security.SecureRandom;

@UtilityClass
public class CryptoUtils {

    public static String randomKey(final int length) {
        final byte[] apiKey = new byte[length];
        final SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(apiKey);

        final StringBuilder sb = new StringBuilder();
        for (final byte b : apiKey) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
}
