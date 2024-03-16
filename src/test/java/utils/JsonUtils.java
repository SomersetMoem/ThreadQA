package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.File;

public class JsonUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    @SneakyThrows
    public static <T> T fromJsonFile(String jsonPath, Class<T> clazz) {
        return mapper.readValue(new File(jsonPath), clazz);
    }

    @SneakyThrows
    public static <T> T fromJsonString(String json, Class<T> clazz) {
        return mapper.readValue(json, clazz);
    }

    @SneakyThrows
    public static String toJson(Object object) {
        return mapper.writeValueAsString(object);
    }
}
