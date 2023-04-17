package com.example.demo.lang;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * @date: 2023/4/10
 * @author: YL
 */
public class StringTest {
    
    @Test
    public void split() {
        String ipHome=",23.0578192";

        String[] split = ipHome.split(",");

        System.out.println(Arrays.asList(split));
        
        ipHome="23.0578192,";
                
        split = ipHome.split(",");

        System.out.println(Arrays.asList(split));
    }

}
