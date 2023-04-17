package com.example.demo.date;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import org.junit.jupiter.api.Test;

/**
 * @date: 2023/2/17
 * @author: YL
 */
public class DateParseUtil {

    @Test
    public void dateParse() {
        String str = "2022-02-01";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        System.out.println(Date.from(LocalDate.parse(str, formatter)
                .with(TemporalAdjusters.firstDayOfMonth())
                .atStartOfDay().toInstant(ZoneOffset.ofHours(8))));
    }
    
    @Test
    public void testInstanceOf() {
        System.out.println(null instanceof Boolean);
    }
}
