package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @date: 2023/3/13
 * @author: YL
 */
@Slf4j
public class PrintTest {
    
    @Test
    public void printNullTest() {
        Integer integer = null;
        System.out.println("integer:" + integer);
    } 

}
