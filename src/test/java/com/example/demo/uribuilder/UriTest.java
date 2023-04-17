package com.example.demo.uribuilder;

import org.junit.jupiter.api.Test;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @date: 2022/9/14
 */
public class UriTest {


    @Test
    public void uriTest() {

        String uriStr = UriComponentsBuilder.fromUriString("requestUrl")
                .queryParam("page", 10)
                .queryParam("size", 10)
                .queryParam("account_id", "123")
                .build(true)
                .toString();

    }

}
