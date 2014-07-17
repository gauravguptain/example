package com.example.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
//import com.google.common.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Map;

public class JsonUtils {

//    private static final Type MAP_TYPE = new TypeToken<Map<String, Object>>() {
//    }.getType();

    public static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.configure(SerializationFeature.INDENT_OUTPUT, true);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.disable(MapperFeature.USE_GETTERS_AS_SETTERS);
        OBJECT_MAPPER.enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
        OBJECT_MAPPER.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
    }

    private JsonUtils() {
    }

    public static String toJson(Object value) {
        try {
            return OBJECT_MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJson(String value, Type type) {
        JavaType javaType = getJavaType(type);
        try {
            return OBJECT_MAPPER.readValue(value, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJson(InputStream inputStream, Type type) {
        JavaType javaType = getJavaType(type);
        try {
            return OBJECT_MAPPER.readValue(inputStream, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public static Map<String, Object> convertToMap(Object value) {
//        JavaType javaType = getJavaType(MAP_TYPE);
//        return OBJECT_MAPPER.convertValue(value, javaType);
//    }

    private static JavaType getJavaType(Type type) {
        return OBJECT_MAPPER.constructType(type);
    }
}
