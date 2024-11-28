package com.literalura.Literalura.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonFileManager {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public <T> T loadFromJson(String fileName, Class<T> clazz) {
        try {
            return objectMapper.readValue(new File(fileName), clazz);
        } catch (IOException e) {
            return null;
        }
    }

    public void saveToJson(String fileName, Object data) {
        try {
            objectMapper.writeValue(new File(fileName), data);
        } catch (IOException e) {
            throw new RuntimeException("Error saving to file: " + e.getMessage());
        }
    }

    public <T> T loadFromJsonString(String jsonString, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            throw new RuntimeException("Error processing JSON string: " + e.getMessage());
        }
    }
}
