package com.example.demo.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author: gaoyunlong
 * @date: 2022/2/21
 */
public enum NumberEnum {
    ONE(1),
    TWO(2);

    private Integer code;

    @JsonValue
    public Integer getCode() {
        return code;
    }

    @JsonCreator
    public static NumberEnum get(@JsonProperty(value = "order") Integer code) {
        for (NumberEnum value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }

        return null;
    }

    NumberEnum(Integer code) {
        this.code = code;
    }
}
