package com.example.demo;

import org.junit.jupiter.api.Test;

/**
 * @date: 2022/7/28
 */
public class NumberTest {

    @Test
    public void number() {
        int i = 10;
        Integer j = null;
        System.out.println(1 == j);
        System.out.println( j == i);
    }

    @Test
    public void testNull() {
        int i = 10;
        Integer j = null;

        System.out.println(Integer.valueOf(i).equals(j));
    }

    @Test
    public void testShiftOperation() {
        System.out.println(2 << 3);
    }

}
