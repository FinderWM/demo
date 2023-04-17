package com.example.demo;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @date: 2023/1/4
 * @author: YL
 */
@Slf4j
public class DateTest {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault());
    
    @Test
    public void testDateValid() {
//        boolean valid = isBetweenOneMonthAndInThreeYears("2019-01-04", "2019-01-05");
//        log.info("2019 result:{}", valid);
//        valid = isBetweenOneMonthAndInThreeYears("2020-02-04", "2020-03-03");
//        log.info("2020 result:{}", valid);
//        valid = isBetweenOneMonthAndInThreeYears("2021-01-04", "2021-02-05");
//        log.info("2021 result:{}", valid);
//        valid = isBetweenOneMonthAndInThreeYears("2022-01-04", "2022-02-04");
//        log.info("2022 result:{}", valid);

        boolean valid1 = isBetweenOneMonthAndInThreeYears("2023-01-04", "2023-01-04");
        log.info("2023 result:{}", valid1);
        
        
        
    }
    
    private boolean isBetweenOneMonthAndInThreeYears(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate, FORMATTER);
        LocalDate end = LocalDate.parse(endDate, FORMATTER);
        if (start.plusMonths(1L).isBefore(end)) {
            return false;
        }

        return isInTreeYears(start) && isInTreeYears(end);
    }

    private boolean isInTreeYears(LocalDate date) {
        return LocalDate.now(ZoneId.systemDefault()).minusYears(3L).isBefore(date);
    }

}
