package com.example.demo.type;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @date: 2022/8/30
 */
@Slf4j
public class TargetHandler {

    public <T> void process(TargetType<T> target) {

        Type actualTypeArgument = ((ParameterizedType) target.getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        log.info(actualTypeArgument.getTypeName());

        TypeReference<T> tTypeReference = new TypeReference<T>(){};

        log.info(tTypeReference.getType().getTypeName());
    }

}
