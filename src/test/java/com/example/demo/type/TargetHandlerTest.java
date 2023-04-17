package com.example.demo.type;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class TargetHandlerTest {

    @Test
    public void test() {
        ConcreteTargetType concreteTargetType = new ConcreteTargetType();

        Type[] genericInterfaces = concreteTargetType.getClass().getGenericInterfaces();
        for (Type genericInterface : genericInterfaces) {
            System.out.println(genericInterface);
        }

        ParameterizedType type = (ParameterizedType) genericInterfaces[0];
        System.out.println(type.getActualTypeArguments()[0]);
    }

    @Test
    public void test1() {
        System.out.println(1 << 7);
    }
}