package com.example.demo;

import cn.hutool.core.bean.BeanUtil;
import com.example.demo.hutool.BeanA;
import com.example.demo.hutool.BeanB;
import com.example.demo.hutool.IntegerComponent;
import com.example.demo.hutool.LongComponent;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @date: 2022/6/30
 */
@Slf4j
public class HuToolConvertTest {

    @Test
    public void intConvertToLong() {

        IntegerComponent component = new IntegerComponent();
        component.setTarget(11);

        log.info("int:{}", component.getTarget());

        LongComponent longComponent = new LongComponent();

        BeanUtil.copyProperties(component, longComponent);

        log.info("long:{}", longComponent.getTarget());
    }

    @Test
    public void longConvertToInt() {

        LongComponent component = new LongComponent();
        component.setTarget(11L);

        log.info("long:{}", component.getTarget());
        IntegerComponent integerComponent = new IntegerComponent();
        BeanUtil.copyProperties(component, integerComponent);

        log.info("int:{}", integerComponent.getTarget());
    }

    @Test
    public void testAuto() {
        BeanB beanB = new BeanB();
        beanB.setLatitude("");

        BeanUtil.copyProperties(beanB, new BeanA());
    }
}
