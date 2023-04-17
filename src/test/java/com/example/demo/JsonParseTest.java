package com.example.demo;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * @date: 2022/9/24
 */
public class JsonParseTest {

    @Test
    public void jsonParse() {
        String source = "[\"\\\"id1\\\"\"]";

        ObjectMapper objectMapper = new ObjectMapper();
        String[] str = new String[0];
        try {
            Object o = objectMapper.readValue(source, str.getClass());

            System.out.println(Arrays.toString((String[])o));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
