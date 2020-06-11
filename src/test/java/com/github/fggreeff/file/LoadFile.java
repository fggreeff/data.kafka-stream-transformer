package com.github.fggreeff.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.util.ResourceUtils;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@AllArgsConstructor
public class LoadFile {

    @SneakyThrows
    public static <T> T loadObjectFromFile(String filePath, Class<T> targetClass) {
        var file = ResourceUtils.getFile("classpath:".concat(filePath));
        var fileString = Files.readString(file.toPath(), StandardCharsets.UTF_8);
        final ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(fileString, targetClass);
    }

    @SneakyThrows
    public static String loadStringFromFile(String filePath) {
        var file = ResourceUtils.getFile("classpath:".concat(filePath));
        return Files.readString(file.toPath(), StandardCharsets.UTF_8);
    }
}
