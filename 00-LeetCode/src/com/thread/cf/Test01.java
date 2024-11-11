package com.thread.cf;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.*;

/**
 * CompletableFuture 使用
 *
 * @author liangfengyuan1024@gmail.com
 * @version 1.0.0
 * @since 2024/3/3 22:16
 */
public class Test01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> runAsyncFuture = CompletableFuture.runAsync(() -> {
            try {
                System.out.println("test task is running");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        // Runnable 任务没有返回值，只会阻塞等待完成
        runAsyncFuture.get();
        CompletableFuture<String> supplyAsyncFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "test callable task";
        });
        // 阻塞获取返回值
        String result = supplyAsyncFuture.get();
        System.out.println(result);
    }
}
