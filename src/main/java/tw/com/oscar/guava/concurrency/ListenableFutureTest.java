/**
 * ListenableFutureTest.java
 * Title: DTS Project
 * Copyright: Copyright(c)2016, Acer
 *
 * @author Oscar Wei
 * @since 2016/1/23
 * <p>
 * H i s t o r y
 * 2016/1/23 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.guava.concurrency;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.*;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.*;

/**
 * <p>
 * Title: ListenableFutureTest.java<br>
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
 * @version v1, 2016/1/23
 * @since 2016/1/23
 */
public class ListenableFutureTest {

    private static final int MAX_SIZE = 10;
    private List<String> list = Lists.newArrayList();
    ExecutorService cachedExecutor = Executors.newCachedThreadPool();
    ExecutorService fixedExecutor = Executors.newFixedThreadPool(MAX_SIZE);
    ListeningExecutorService listeningExecutor = MoreExecutors.listeningDecorator(fixedExecutor);


    public void addToList(String item) throws ExecutionException, InterruptedException {
        Future<String> future = cachedExecutor.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                return "";
            }
        });
        String returnValue = future.get();
        list.add(returnValue);
    }

    public void addToListListenableFutureVersion(String item) {
        ListenableFuture<String> listenableFuture = listeningExecutor.submit(() -> "");
        listenableFuture.addListener(new Runnable() {

            @Override
            public void run() {
                methodToRunOnFutureTaskCompletion();
            }
        }, listeningExecutor);
    }

    public void addToListListenableFutureWithFutureCallbackVersion(String item) {
        FutureCallbackImpl callback = new FutureCallbackImpl();
        ListenableFuture<String> futureTask = listeningExecutor.submit(() -> "");
        Futures.addCallback(futureTask, callback);
        // Futures.addCallback(futureTask, callback, cachedExecutor);
        callback.getCallbackResult();
    }

    public void addToListSettableFutureVersion(String item) {
        SettableFuture<String> sf = SettableFuture.create();
        // Set a value to return
        sf.set("Success");
        // Set a failure custom exception
        sf.setException(new Exception(""));
        Runnable runnable = () -> {
            //
        };
        sf.addListener(runnable, cachedExecutor);
    }

    public void FuturesTest() {
        ListenableFuture<String> futureTask = listeningExecutor.submit(() -> "");
        AsyncFunctionSample af = new AsyncFunctionSample();
        // ListenableFuture<String> listenableFuture = Futures.transform(futureTask, af);
    }

    static class FutureCallbackImpl implements FutureCallback<String> {

        private StringBuilder builder = new StringBuilder();

        @Override
        public void onSuccess(String result) {
            builder.append(result).append(" successfully");
        }

        @Override
        public void onFailure(Throwable t) {
            builder.append(t.toString());
        }

        public String getCallbackResult() {
            return builder.toString();
        }
    }

    static class AsyncFunctionSample implements AsyncFunction<Long, String> {

        private ConcurrentMap<Long, String> map = Maps.newConcurrentMap();
        private ListeningExecutorService listeningExecutorService;

        @Override
        public ListenableFuture<String> apply(final Long input) throws Exception {
            if (map.containsKey(input)) {
                SettableFuture<String> listenableFuture = SettableFuture.create();
                listenableFuture.set(map.get(input));
                return listenableFuture;
            } else {
                return listeningExecutorService.submit(new Callable<String>() {

                    @Override
                    public String call() throws Exception {
                        String value = ""; // to something
                        map.putIfAbsent(input, value);
                        return value;
                    }
                });
            }
        }
    }

    static class FutureFallbackSample implements FutureFallback<String> {

        @Override
        public ListenableFuture<String> create(Throwable t) throws Exception {
            if (t instanceof FileNotFoundException) {
                SettableFuture<String> settableFuture = SettableFuture.create();
                settableFuture.set("Not found");
                return settableFuture;
            }
            throw new Exception(t);
        }
    }

    private void methodToRunOnFutureTaskCompletion() {
        //
    }
}
