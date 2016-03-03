/**
 * ExecutorServiceTest.java
 * Title: DTS Project
 * Copyright: Copyright(c)2016, Acer
 *
 * @author Oscar Wei
 * @since 2016/2/29
 * <p>
 * H i s t o r y
 * 2016/2/29 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.jdk.concurrent;

import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * <p>
 * Title: ExecutorServiceTest.java<br>
 * </p>
 * <strong>Description:</strong> //TODO <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2016<br>
 * </p>
 * <p>
 * Company: Acer Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2016/2/29
 * @since 2016/2/29
 */
public class ExecutorServiceTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        int corePoolSize = 5;
        int maxPoolSize = 10;
        long keepAliveTime = 5000;

        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>()
        );

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        ScheduledFuture scheduledFuture = scheduledExecutorService.schedule(() -> {
            System.out.println("Executed!");
            return "Called!";
        }, 5, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(() -> System.out.println("Asynchronous task"), 10, 10, TimeUnit.MINUTES);
        scheduledExecutorService.scheduleWithFixedDelay(() -> System.out.println("Asynchronous task"), 10, 10, TimeUnit.MINUTES);

        scheduledFuture.get();

        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        ExecutorService executorService2 = Executors.newFixedThreadPool(10);
        ExecutorService executorService3 = Executors.newScheduledThreadPool(10);

        Callable<String> callable = () -> "Done";

        executorService1.execute(() -> System.out.println("Asynchronous task"));

        // returns null if the task has finished correctly
        Future future = executorService2.submit(() -> System.out.println("Asynchronous task"));
        System.out.printf(null == future.get() ? "Success" : "Failure");

        Future<String> future1 = executorService2.submit(callable);
        System.out.println(future1.get());

        Callable<String> callable1 = () -> "Task 1";
        Callable<String> callable2 = () -> "Task 2";
        Callable<String> callable3 = () -> "Task 3";
        Set<Callable<String>> callableSet = Sets.newHashSet(callable1, callable2, callable3);
        String result = executorService2.invokeAny(callableSet);
        System.out.println("result = " + result);

        List<Future<String>> futures = executorService2.invokeAll(callableSet);
        for (Future<String> f : futures) {
            System.out.println("future.get = " + f.get());
        }

        executorService1.shutdownNow();
        executorService2.shutdown();
        executorService3.shutdownNow();
    }
}
