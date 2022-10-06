package com.fx.structurePattren.adapter.a;

import lombok.SneakyThrows;

import java.util.concurrent.*;

/**
 * <p>
 *
 * </p>
 *
 * @author 梁峰源
 * @since 2022/10/5 14:28
 */
public class ThreadTest {
    // 线程池，线程任务提交给线程池执行
    public static ExecutorService executorService = Executors.newFixedThreadPool(10);
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 线程池运行Runnable方式
        FutureTask<Class<?>> futureTask01 = new FutureTask<>(new Runnable() {
            @Override
            @SneakyThrows
            public void run() {
                System.out.println("线程池运行Runnable方式");
                Thread.sleep(3000);
            }
        }, String.class);
        executorService.submit(futureTask01);
        // 线程池运行Callable方式
        FutureTask<String> futureTask02 = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                System.out.println("线程池运行Callable方式");
                // 返回一句话
                return "线程池运行Callable方式返回：" + Thread.currentThread().getName();
            }
        });
        executorService.submit(futureTask02);
        System.out.println(futureTask01.get());
        System.out.println(futureTask02.get());
        executorService.shutdown();
    }
}
