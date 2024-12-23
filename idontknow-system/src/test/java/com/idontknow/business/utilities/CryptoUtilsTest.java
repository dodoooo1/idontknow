package com.idontknow.business.utilities;

import com.idontknow.business.core.utilities.CryptoUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CryptoUtilsTest {

    @Test
    void verifyRandomKey() {
        final int length = 10;
        Assertions.assertEquals(length * 2, CryptoUtils.randomKey(length).length());
    }
}
