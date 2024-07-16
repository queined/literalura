package com.queined.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The Deserializer class provides methods to deserialize JSON strings into Java objects.
 */
public class Deserializer {
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Deserializes the given JSON string into an object of the specified class type.
     *
     * @param json      the JSON string to deserialize
     * @param classType the class type to deserialize the JSON into
     * @param <T>       the type of the object to deserialize
     * @return the deserialized object
     * @throws RuntimeException if there is an error deserializing the JSON
     */
    public static <T> T deserialize(String json, Class<T> classType) {
        try {
            return objectMapper.readValue(json, classType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error deserializing JSON", e);
        }
    }
}
