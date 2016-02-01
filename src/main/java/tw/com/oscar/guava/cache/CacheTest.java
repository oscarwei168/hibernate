/**
 * Title: Acer Internal Project
 * Copyright: (c) 2016, Acer Inc.
 * Name: CacheTest
 *
 * @author Oscar Wei
 * @since 2016/1/31
 * <p>
 * H i s t o r y
 * <p>
 * 2016/1/31 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.guava.cache;

import com.google.common.cache.*;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import tw.com.oscar.orm.hibernate.domain.Account;

import java.util.concurrent.*;

/**
 * <strong>Description:</strong><br>
 * This function include: - TODO <br>
 *
 * @author Oscar Wei
 * @version v1, 2016/1/31
 * @since 2016/1/31
 */
public class CacheTest {

    final static ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        Account account = null;
        LoadingCache<String, Account> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(1000).weigher(new Weigher<String, Account>() {
                    @Override
                    public int weigh(String key, Account value) {
                        return value.getRoles().size();
                    }
                }).expireAfterWrite(10, TimeUnit.MINUTES)
                .removalListener(new RemovalListener<String, Account>() {
                    @Override
                    public void onRemoval(RemovalNotification<String, Account> removalNotification) {
                        Account account = removalNotification.getValue();
                        // TODO
                    }
                }).refreshAfterWrite(1, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Account>() {
                    @Override
                    public Account load(String s) throws Exception {
                        return null;
                    }

                    @Override
                    public ListenableFuture<Account> reload(final String key, Account account) {
                        if (true) {
                            return Futures.immediateFuture(account);
                        } else {
                            ListenableFutureTask<Account> task = ListenableFutureTask.create(new Callable<Account>() {
                                public Account call() {
                                    return null;
                                }
                            });
                            executor.execute(task);
                            return task;
                        }
                    }
                });
        // ....
        CacheStats cacheStats = loadingCache.stats();
        try {
            account = loadingCache.get("aid");
            // ImmutableMap<String, Account> accountList = loadingCache.getAll(Lists.newArrayList("", ""));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        /** if load() does not declare any checked exception then use getUnchecked() method **/
        // account = loadingCache.getUnchecked("aid");

        Cache<String, Account> loadingCache1 = CacheBuilder.newBuilder().maximumSize(1000).build();
        // ....
        try {
            account = loadingCache1.get("aid", new Callable<Account>() {
                @Override
                public Account call() throws Exception {
                    return null;
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // inserted directly
        loadingCache1.put("aid1", account);

        // explicit removals
        loadingCache.invalidate("");
        loadingCache.invalidateAll(Lists.newArrayList("", ""));

    }
}
