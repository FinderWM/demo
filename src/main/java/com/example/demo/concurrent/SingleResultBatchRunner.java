package com.example.demo.concurrent;

import java.util.ArrayList;
import java.util.function.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

/**
 * 批量执行器，只支持全部成功
 *
 * @date: 2022/9/6
 */
@Slf4j
public class SingleResultBatchRunner<T> {

    private final BinaryOperator<T> combiner;

    private final ExecutorService executorService;

    private final AtomicReference<T> resultData;

    private final AtomicInteger fail = new AtomicInteger(0);

    private final AtomicInteger success = new AtomicInteger(0);

    private final CompletableFuture<Boolean> result = new CompletableFuture<>();

    private int taskSize;

    private final StopWatch stopWatch;

    /**
     * 任务结果聚合后操作
     */
    private Consumer<T> consumerAfterCombiner;

    private Collection<CompletableFuture<Void>> futures;

    /**
     * 标致 例如：批量获取某某结果
     */
    private final String sign;

    public SingleResultBatchRunner(ExecutorService executorService, T data, BinaryOperator<T> combiner, String sign) {
        this.executorService = executorService;
        this.resultData = new AtomicReference<>(data);
        this.combiner = combiner;
        this.sign = sign;
        stopWatch = new StopWatch();
    }

    public void setConsumerAfterCombiner(Consumer<T> consumerAfterCombiner) {
        this.consumerAfterCombiner = consumerAfterCombiner;
    }

    public void batchRun(Collection<Supplier<T>> suppliers) {
        stopWatch.start(sign + " batch runner");
        this.taskSize = suppliers.size();

        futures = new ArrayList<>(taskSize);

        if (log.isDebugEnabled()) {
            log.debug("{} 开始, 任务数:{}", sign, taskSize);
        }

        for (Supplier<T> supplier : suppliers) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                if (isStop()) {
                    log.info("run:任务已取消");
                    return;
                }

                resolveData(supplier.get());
            }, executorService)
                    .whenComplete((aVoid, throwable) -> {
                        if (Objects.isNull(throwable)) {
                            completedCheck(Boolean.TRUE);
                            return;
                        }

                        log.warn("{} run failed, msg:{}", sign, throwable.getMessage());
                        completedCheck(Boolean.FALSE);
                    });

            futures.add(future);
        }
    }

    private void resolveData(T t) {
        if (isStop()) {
            log.info("resolveData:任务已取消");
            return;
        }

        resultData.accumulateAndGet(t, combiner);

        if (Objects.nonNull(consumerAfterCombiner)) {
            consumerAfterCombiner.accept(t);
        }
    }

    /**
     * 提前结束任务
     *
     * @param resultFlag
     */
    public void cancel(boolean resultFlag) {
        if (isStop()) {
            log.info("cancel:任务已取消");
            return;
        }

        futures.forEach(o -> {
            if (o.isDone() || o.isCancelled() || o.isCompletedExceptionally()) {
                return;
            }

            o.cancel(false);
        });

        log.info("cancel {} task, resultFlag:{}", sign, resultFlag);
        
        completed(resultFlag);
    }

    private void completedCheck(boolean runFlag) {

        if (isStop()) {
            log.info("check:任务已取消");
            return;
        }

        if (runFlag) {

            success.incrementAndGet();

            if (log.isDebugEnabled()) {
                log.debug("taskSize:{}, success:{}", taskSize, success.get());
            }

            if (success.get() == taskSize) {
                completed(Boolean.TRUE);
            }

            return;
        }

        fail.incrementAndGet();

        completed(Boolean.FALSE);
    }

    private void completed(Boolean flag) {

        if (!result.isDone() && result.complete(flag)) {
            stopWatch.stop();

            log.info("task:{} run completed,flag:{},task size:{},run time: {} ms", sign, flag, taskSize, stopWatch.getLastTaskTimeMillis());
        }
    }

    /**
     * 不超时获取
     *
     * @return
     */
    public T get() {

        if (result.isCompletedExceptionally()) {
            return null;
        }

        try {
            if (Boolean.TRUE.equals(result.get())) {
                return resultData.get();
            }
        } catch (Exception e) {
            log.warn("get result of {} runner failed, msg:{}", sign, e.getMessage());
        }

        return null;
    }

    public T get(long timeOut, TimeUnit timeUnit) {

        try {
            if (result.isCompletedExceptionally()) {
                return null;
            }

            if (Boolean.TRUE.equals(result.get(timeOut, timeUnit))) {
                return resultData.get();
            }
        } catch (Exception e) {
            log.warn("get result of {} runner failed, timeout:{} ms, msg:{}", sign, timeUnit.toMillis(timeOut), e.getMessage());
        }

        return null;
    }

    private boolean isStop() {
        return result.isDone() || fail.get() > 0;
    }

}
