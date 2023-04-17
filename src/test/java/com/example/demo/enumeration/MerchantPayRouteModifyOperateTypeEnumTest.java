package com.example.demo.enumeration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class MerchantPayRouteModifyOperateTypeEnumTest {

    @Test
    void valuesMap() {

        Runnable execute = () -> {
            Map<Integer, MerchantPayRouteModifyOperateTypeEnum> map = MerchantPayRouteModifyOperateTypeEnum.valuesMap();

            log.info("size:{}, context:{}", map.size(), map);
        };

        IntStream.range(1, 10).boxed().map(j -> new Thread(execute)).forEach(Thread::start);
    }
}