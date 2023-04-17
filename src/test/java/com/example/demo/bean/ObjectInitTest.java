package com.example.demo.bean;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @date: 2023/2/8
 * @author: YL
 */
public class ObjectInitTest {
    
    @Slf4j
    public static class InitObject {
        
        private MarkObject o1 = new MarkObject("o1", this);

        {
          log.info("first locate code block init");  
        }

        private String initStr = "no null";
        
        private MarkObject o2 = new MarkObject("o2", this);

        public InitObject() {
            log.info("InitObject init");
        }

        {
            log.info("end locate code block init,{}", this.initStr);
        }

        public String getInitStr() {
            return initStr;
        }
    }

    @Slf4j
    public static class MarkObject {
        
        public MarkObject(String mark, InitObject initObject) {
            log.info("mark object init, mark:{}, get:{}", mark, initObject.getInitStr());
        }
        
    }
    
    @Test
    public void testInitOrder() {
        new InitObject();
    }
}
