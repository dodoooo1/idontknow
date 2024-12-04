package com.idontknow.business.utilities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.Getter;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * This is a Helper class with commonly used utilities for the SDK.
 */
@Getter
public class CoreJsonUtils {

    /**
     * Deserialization of Json data.
     * -- GETTER --
     * Deserialization of Json data.
     *
     * @return {@link ObjectMapper}.
     */
    @Getter
    private static final ObjectMapper mapper = JsonMapper
            .builder().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false)
            .withConfigOverride(BigDecimal.class, mutableConfigOverride -> mutableConfigOverride
                    .setFormat(JsonFormat.Value.forShape(JsonFormat.Shape.STRING)))
            .build();

    /**
     * Strict Deserialization of Json data.
     */
    private static final ObjectMapper strictMapper = JsonMapper.builder()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, true)
            .configure(MapperFeature.ALLOW_COERCION_OF_SCALARS, false)
            .withConfigOverride(BigDecimal.class, mutableConfigOverride -> mutableConfigOverride
                    .setFormat(JsonFormat.Value.forShape(JsonFormat.Shape.STRING)))
            .build();

    private CoreJsonUtils() {
    }


    /**
     * Json Serialization of a given object.
     *
     * @param obj The object to serialize into Json.
     * @return The serialized Json String representation of the given object.
     * @throws JsonProcessingException Signals that a Json Processing Exception has
     *                                 occurred.
     */
    public static String serialize(Object obj) throws JsonProcessingException {
        if (obj == null) {
            return null;
        }

        return mapper.writeValueAsString(obj);
    }

    /**
     * Json Serialization of a given object using a specified JsonSerializer.
     *
     * @param obj        The object to serialize into Json.
     * @param serializer The instance of JsonSerializer to use.
     * @return The serialized Json string representation of the given object.
     * @throws JsonProcessingException Signals that a Json Processing Exception has
     *                                 occurred.
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static String serialize(Object obj, final JsonSerializer serializer)
            throws JsonProcessingException {
        if (obj == null || serializer == null) {
            return null;
        }

        Class<? extends Object> cls = null;
        if (obj.getClass().getName().equals("java.util.ArrayList")) {
            // need to find the generic type if it's an ArrayList
            cls = ((ArrayList) obj).get(0).getClass();
        } else if (obj.getClass().getName().equals("java.util.LinkedHashMap")) {
            cls = ((LinkedHashMap) obj).values().toArray()[0].getClass();
        } else {
            cls = obj.getClass();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(cls, serializer);
        objectMapper.registerModule(module);

        return objectMapper.writeValueAsString(obj);
    }

    /**
     * Json deserialization of the given Json string.
     *
     * @param <T>   The type of the object to deserialize into.
     * @param json  The Json string to deserialize.
     * @param clazz The type of the object to deserialize into.
     * @return The deserialized object.
     * @throws IOException Signals if any I/O exception occurred.
     */
    public static <T extends Object> T deserialize(String json, Class<T> clazz) throws IOException {
        if (isNullOrWhiteSpace(json)) {
            return null;
        }

        return mapper.readValue(json, clazz);
    }

    /**
     * JSON Deserialization of the given json string.
     *
     * @param json          The json string to deserialize.
     * @param typeReference TypeReference of T.
     * @param <T>           The type of the object to deserialize into.
     * @return The deserialized object.
     * @throws IOException Signals if any I/O exception occurred.
     */
    public static <T extends Object> T deserialize(String json, TypeReference<T> typeReference)
            throws IOException {
        if (isNullOrWhiteSpace(json)) {
            return null;
        }

        return mapper.readValue(json, typeReference);
    }

    /**
     * JSON Deserialization of the given json string with FAIL_ON_UNKNOWN_PROPERTIES
     * flag as true.
     *
     * @param jsonNode      The Json Node to deserialize.
     * @param typeReference TypeReference of T.
     * @param <T>           The type of the object to deserialize into.
     * @return The deserialized object.
     * @throws IOException Signals if any I/O exception occurred.
     */
    public static <T extends Object> T deserialize(JsonNode jsonNode,
                                                   TypeReference<T> typeReference) throws IOException {
        if (jsonNode == null) {
            return null;
        }

        return strictMapper.convertValue(jsonNode, typeReference);
    }

    /**
     * JSON deserialization of the given JsonNode with FAIL_ON_UNKNOWN_PROPERTIES
     * flag as true.
     *
     * @param <T>      The type of the object to deserialize into.
     * @param jsonNode The Json Node to deserialize.
     * @param clazz    The type of the object to deserialize into.
     * @return The deserialized object.
     * @throws IOException Signals if any I/O exception occurred.
     */
    public static <T extends Object> T deserialize(JsonNode jsonNode, Class<T> clazz)
            throws IOException {
        if (jsonNode == null) {
            return null;
        }
        return strictMapper.convertValue(jsonNode, clazz);
    }


    /**
     * Json deserialization of the given Json string.
     *
     * @param json The Json string to deserialize.
     * @return The deserialized Json as an Object.
     */
    public static Object deserializeAsObject(String json) {
        if (isNullOrWhiteSpace(json)) {
            return null;
        }
        try {
            return CoreJsonUtils.deserialize(json, new TypeReference<Object>() {
            });
        } catch (IOException e) {
            // Failed to deserialize when json is not representing a JSON object.
            // i.e. either its string or any primitive type.
            return json;
        }
    }

    /**
     * JSON Deserialization of the given json string.
     *
     * @param <T>        The type of the object to deserialize into.
     * @param json       The Json string to deserialize.
     * @param classArray The class of the array of objects to deserialize into.
     * @return The deserialized list of objects.
     * @throws IOException Signals if any I/O exception occurred..
     */
    public static <T extends Object> List<T> deserializeArray(String json, Class<T[]> classArray)
            throws IOException {
        if (isNullOrWhiteSpace(json)) {
            return null;
        }

        return Arrays.asList(mapper.readValue(json, classArray));
    }


    /**
     * Validates if the string is null, empty or whitespace.
     *
     * @param s The string to validate.
     * @return The result of validation.
     */
    public static boolean isNullOrWhiteSpace(String s) {
        if (s == null) {
            return true;
        }

        int length = s.length();
        if (length > 0) {
            for (int start = 0, middle = length / 2, end = length
                    - 1; start <= middle; start++, end--) {
                if (s.charAt(start) > ' ' || s.charAt(end) > ' ') {
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    /**
     * Replaces all occurrences of the given string in the string builder.
     *
     * @param stringBuilder The string builder to update with replaced strings.
     * @param toReplace     The string to replace in the string builder.
     * @param replaceWith   The string to replace with.
     */
    public static void replaceAll(StringBuilder stringBuilder, String toReplace,
                                  String replaceWith) {
        int index = stringBuilder.indexOf(toReplace);

        while (index != -1) {
            stringBuilder.replace(index, index + toReplace.length(), replaceWith);
            index += replaceWith.length(); // Move to the end of the replacement
            index = stringBuilder.indexOf(toReplace, index);
        }
    }


    /**
     * JSON Deserialization of the given json string with FAIL_ON_UNKNOWN_PROPERTIES
     * flag as true.
     *
     * @param <T>        The type of the object to deserialize into.
     * @param json       The Json string to deserialize.
     * @param classArray The class of the array of objects to deserialize into.
     * @return The deserialized list of objects.
     * @throws IOException Signals if any I/O exception occurred.
     */
    public static <T extends Object> List<T> deserializeArray(JsonNode json, Class<T[]> classArray)
            throws IOException {
        if (json == null) {
            return null;
        }

        return Arrays.asList(strictMapper.convertValue(json, classArray));
    }

}
