package com.example.demo.helper;

import static java.util.Optional.ofNullable;

/**
 * @date: 2022/3/10
 */
public class OptionalDemo {

    public static void main(String[] args) {
        ofNullable(null).map(o -> o.toString()).orElseThrow(() -> new RuntimeException("hhh"));
    }
}
