package com.example.demo.starter;

import com.example.demo.DemoApplication;
import com.example.demo.async.AsyncTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@ComponentScan(basePackages = {"com.example.demo.async"})
public class AsyncTestStarter {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);

        AsyncTest bean = context.getBean(AsyncTest.class);
        System.out.println(bean);
        bean.testVoid();
        System.out.println(bean.testObject());
        System.out.println(bean.testFuture());
    }
}
