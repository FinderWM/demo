package com.example.demo.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

/**
 * @author: gaoyunlong
 * @date: 2022/2/21
 */
@Getter
@Setter
public class JsonBody {

    private String name;

    @JsonProperty(value = "order")
    private NumberEnum orderValue;

    public static void main(String[] args) {
        JsonBody body = new JsonBody();
        body.setName("name");
        body.setOrderValue(NumberEnum.ONE);

        try {
            String jsonStr = new ObjectMapper().writeValueAsString(body);
            System.out.println(jsonStr);
            JsonBody readValue = new ObjectMapper().readValue(jsonStr, JsonBody.class);
            System.out.println(readValue.getName());
            System.out.println(readValue.getOrderValue().getCode());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
