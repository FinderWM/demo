package com.example.demo;

import cn.hutool.core.text.UnicodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

/**
 * @date: 2022/9/5
 */
@Slf4j
public class UriTest {


    @Test
    public void uriBuild() {

        UriComponents build = UriComponentsBuilder.fromUriString("https://www.baidu.com")
                .queryParam("param1", 111)
                .queryParam("param2", 222)
                .build(true);


        build = UriComponentsBuilder.fromUriString("https://www.baidu.com")
                .buildAndExpand();

        log.info(build.toString());

        build.expand(Collections.singletonMap("param1", 333));

        log.info(build.toString());

    }
    
    @Test
    public void testStr() {
        String unicodeStr = "\\u5317\\u4EAC\\u5E02,\\u4E0A\\u6D77\\u5E02,\\u676D\\u5DDE\\u5E02,\\u6210\\u90FD\\u5E02,\\u6DF1\\u5733\\u5E02,\\u91CD\\u5E86\\u5E02,\\u6B66\\u6C49\\u5E02,\\u5357\\u4EAC\\u5E02,\\u897F\\u5B89\\u5E02,\\u5E7F\\u5DDE\\u5E02,\\u5929\\u6D25\\u5E02,\\u82CF\\u5DDE\\u5E02,\\u957F\\u6C99\\u5E02,\\u90D1\\u5DDE\\u5E02,\\u5408\\u80A5\\u5E02,\\u6C88\\u9633\\u5E02,\\u65E0\\u9521\\u5E02,\\u5927\\u8FDE\\u5E02,\\u8D35\\u9633\\u5E02,\\u6606\\u660E\\u5E02,\\u54C8\\u5C14\\u6EE8\\u5E02,\\u5B81\\u6CE2\\u5E02,\\u798F\\u5DDE\\u5E02,\\u957F\\u6625\\u5E02,\\u9752\\u5C9B\\u5E02,\\u5357\\u660C\\u5E02,\\u53A6\\u95E8\\u5E02,\\u6D4E\\u5357\\u5E02,\\u4F5B\\u5C71\\u5E02,\\u5E38\\u5DDE\\u5E02,\\u5357\\u5B81\\u5E02,\\u77F3\\u5BB6\\u5E84\\u5E02,\\u592A\\u539F\\u5E02,\\u4E1C\\u839E\\u5E02,\\u6E29\\u5DDE\\u5E02,\\u4E09\\u4E9A\\u5E02,\\u6D77\\u53E3\\u5E02,\\u6CC9\\u5DDE\\u5E02,\\u5357\\u901A\\u5E02,\\u91D1\\u534E\\u5E02,\\u4E4C\\u9C81\\u6728\\u9F50\\u5E02,\\u7ECD\\u5174\\u5E02,\\u547C\\u548C\\u6D69\\u7279\\u5E02,\\u5609\\u5174\\u5E02,\\u53F0\\u5DDE\\u5E02,\\u6D1B\\u9633\\u5E02,\\u6606\\u5C71\\u5E02,\\u60E0\\u5DDE\\u5E02,\\u5F90\\u5DDE\\u5E02,\\u626C\\u5DDE\\u5E02,\\u4E34\\u6C82\\u5E02,\\u6F4D\\u574A\\u5E02,\\u70DF\\u53F0\\u5E02,\\u5170\\u5DDE\\u5E02,\\u5510\\u5C71\\u5E02,\\u4E2D\\u5C71\\u5E02,\\u897F\\u5B81\\u5E02,\\u73E0\\u6D77\\u5E02,\\u94F6\\u5DDD\\u5E02,\\u7EF5\\u9633\\u5E02,\\u76D0\\u57CE\\u5E02,\\u4FDD\\u5B9A\\u5E02,\\u5305\\u5934\\u5E02,\\u6DEE\\u5B89\\u5E02";
        String str = UnicodeUtil.toString(unicodeStr);
        log.info(str);
        log.info("result:{}", str.contains("珠海市"));
    }
}
