package com.example.demo;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @date: 2022/12/28
 * @author: YL
 */
@Slf4j
public class BitRecordTest {

    private static final String KEY_PATTERN = "%s%d:%d:%d";

    private static final int BUCKET = (1 << 5) - 1;
    
    @Test
    public void concatKey() {
        long adUserId = 1234567890;
        log.info("release:{}, bucket:{}", adUserId % (BUCKET + 1), BUCKET);

        log.info("-----------------------");
        
        String key1 = generateKey("start:", 1, adUserId);
        log.info("key:{}", key1);
        key1 = generateKey("start:", 1, adUserId - 5);
        log.info("key:{}", key1);
        String key3 = generateKey("start:", 2, adUserId + 3);
        log.info("key:{}", key3);
        key3 = generateKey("start:", 2, adUserId - 3);
        log.info("key:{}", key3);

        log.info("-----------------------");
        
        long tomorrowStart = unixTimestampOfTomorrowStart();
        log.info("tomorrow:{}", tomorrowStart);

        log.info("-----------------------");
        
        log.info("0 | 1:{}", 0 | 1);
        log.info("0 | 2:{}", 0 | 2);
        log.info("1 | 1:{}", 1 | 1);
        log.info("1 | 2:{}", 1 | 2);
        log.info("2 | 1:{}", 2 | 1);
        log.info("2 | 2:{}", 2 | 2);
        log.info("3 | 1:{}", 3 | 1);
        log.info("3 | 2:{}", 3 | 2);
        
        log.info("-----------------------");
        
        log.info("0 & 1:{}", 0 & 1);
        log.info("0 & 2:{}", 0 & 2);
        log.info("1 & 1:{}", 1 & 1);
        log.info("1 & 2:{}", 1 & 2);
        log.info("2 & 1:{}", 2 & 1);
        log.info("2 & 2:{}", 2 & 2);
        log.info("3 & 1:{}", 3 & 1);
        log.info("3 & 2:{}", 3 & 2);
    } 
    
    private String generateKey(String prefix, int type, long adUserId) {
        return String.format(KEY_PATTERN, prefix, findBucket(adUserId), type, getDay());
    }

    private int getDay() {
        return LocalDate.now(ZoneId.systemDefault()).getDayOfMonth();
    }

    private long findBucket(Long adUserId) {
        return adUserId & BUCKET;
    }

    private long unixTimestampOfTomorrowStart() {
        LocalDate startOfTomorrow = LocalDate.now(ZoneId.systemDefault()).plusDays(1L);
        return startOfTomorrow.atStartOfDay().toEpochSecond(ZoneOffset.ofHours(8)) + new Random().nextInt(30);
    }
    
}
