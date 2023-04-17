package com.example.demo.number;

import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @date: 2022/12/17
 * @author: YL
 */
@Slf4j
public class BigdecimalDivideTest {

    @Test
    public void divide() {
        log.info("7/9:{}", new BigDecimal(7).divide(new BigDecimal(9), 2, RoundingMode.DOWN));
        log.info("4/6:{}", new BigDecimal(4).divide(new BigDecimal(6), 2, RoundingMode.DOWN));
    }
}
