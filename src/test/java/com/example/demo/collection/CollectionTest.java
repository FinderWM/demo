package com.example.demo.collection;

import org.junit.jupiter.api.Test;

import java.util.Collections;

/**
 * @date: 2022/7/27
 */
public class CollectionTest {

    @Test
    public void coll() {

        Collections.emptyList().stream().peek(System.out::println);

    }
}
