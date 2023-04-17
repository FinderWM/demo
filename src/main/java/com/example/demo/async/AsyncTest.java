package com.example.demo.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Component
public class AsyncTest {

    private String name;

    public AsyncTest() {
    }

    public AsyncTest(String name) {
        this.name = name;
    }

    @Async
    public void testVoid() {

    }

    @Async
    public AsyncTest testObject() {
        return new AsyncTest("object");
    }

    @Async
    public Future<AsyncTest> testFuture() {
        return CompletableFuture.supplyAsync(() -> new AsyncTest("future"));
    }

    @Override
    public String toString() {
        return "AsyncTest{" +
                "name='" + name + '\'' +
                '}';
    }
}
