package com.idontknow.business.core.utilities;

import lombok.experimental.UtilityClass;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@UtilityClass
public class UrlUtils {
    public static String encodeURLComponent(final String component) {
        return URLEncoder.encode(component, StandardCharsets.UTF_8);
    }

}
