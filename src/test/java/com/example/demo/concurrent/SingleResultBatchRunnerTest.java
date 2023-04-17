package com.example.demo.concurrent;

import java.security.SecureRandom;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class SingleResultBatchRunnerTest {

    @Test
    void batchRun() {
        List<String> list = IntStream.range(1, 4990001).boxed().map(Object::toString).collect(Collectors.toList());

        System.out.println("size:" + list.size());

        Collection<List<String>> split = split(list, 499);

        System.out.println("batch size:" + split.size());

        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 3);

        SingleResultBatchRunner<Collection<String>> runner = new SingleResultBatchRunner<>(service, Collections.synchronizedList(new ArrayList<>()),
                (x, y) -> {
                    x.addAll(y);
                    return x;
                }, "test");

        Random random = new Random();
        List<Supplier<Collection<String>>> collect = split.stream().map(o -> (Supplier<Collection<String>>) () -> {
            int i = random.nextInt(100);
            if (i > 90) {
//                i = 1 / 0;
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return o;
        }).collect(Collectors.toList());

        runner.batchRun(collect);

        Collection<String> strings = runner.get(100, TimeUnit.MILLISECONDS);

        System.out.println("result size:" + (Objects.isNull(strings) ? 0 : strings.size()));
    }

    @Test
    void cancelTest() {
        List<String> list = IntStream.range(1, 500000).boxed().map(Object::toString).collect(Collectors.toList());

        log.info("size:{}", list.size());

        Collection<List<String>> split = split(list, 499);

        log.info("batch size:{}", split.size());

        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 3);

        SingleResultBatchRunner<Collection<String>> runner = new SingleResultBatchRunner<>(service, Collections.emptyList(),
                (x, y) -> x, "test cancel");
        
        Random random = new SecureRandom();
        List<Supplier<Collection<String>>> collect = split.stream().map(o -> (Supplier<Collection<String>>) () -> {
            int i = random.nextInt(100);
            if (i > 90) {
//                i = 1 / 0;
            }
            try {
                TimeUnit.MILLISECONDS.sleep(i);
            } catch (InterruptedException e) {
                log.error("error", e);
            }

            return o;
        }).collect(Collectors.toList());
        runner.setConsumerAfterCombiner(strings -> {
            if (random.nextInt(100) > 30) {
                runner.cancel(true);
            }
        });
        runner.batchRun(collect);
        
        runner.get(3, TimeUnit.SECONDS);
    }
    
    private Collection<List<String>> split(List<String> data, int batchSize) {
        int batch = new BigDecimal(data.size())
                .divide(new BigDecimal(batchSize), 0, RoundingMode.UP).intValue();

        return IntStream.range(0, batch).boxed()
                .map(i -> {

                    int startIdx = i * batchSize;
                    int endIdx = (i + 1) * batchSize;
                    endIdx = Math.min(endIdx, data.size());

                    return data.subList(startIdx, endIdx);
                })
//                .peek(i -> System.out.println(i.size()))
                .collect(Collectors.toList());
    }
}