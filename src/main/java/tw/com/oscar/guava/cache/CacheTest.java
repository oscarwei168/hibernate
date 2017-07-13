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
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import tw.com.oscar.orm.hibernate.domain.Account;

import java.util.Map;
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
    
    public static void main(String[] args) throws Exception {
        
        /** Simple Loading Cache **/
        CacheLoader<String, String> loader = new CacheLoader<String, String>() {
            
            /**
             * V load(K key)
             **/
            public String load(String key) {
                return key.toUpperCase();
            }
        };
        LoadingCache<String, String> cache = CacheBuilder.newBuilder().maximumSize(100).build(loader);
        System.out.println("Cache size: " + cache.size());
        /** if load() does not declare any checked exception then use getUnchecked() method **/
        System.out.println(cache.getUnchecked("oscar.wei")); // Cache miss
        System.out.println("Cache size: " + cache.size());
        System.out.println(cache.getUnchecked("oscar.wei")); // Cache hit
        System.out.println(cache.getUnchecked("oscar"));
        
        CacheStats cacheStats = cache.stats();
        System.out.println(cacheStats.hitRate());
        System.out.println(cacheStats.missRate());
        System.out.println(cacheStats.loadExceptionRate());
        System.out.println(cacheStats.averageLoadPenalty());
        
        CacheStats delta = cache.stats().minus(cacheStats);
        System.out.println(delta.hitCount());
        System.out.println(delta.missCount());
        System.out.println(delta.loadSuccessCount());
        System.out.println(delta.loadExceptionCount());
        System.out.println(delta.totalLoadTime());
        
        // expireAfterAccess: Eviction-->Time to Idle
        // expireAfterWrite: Eviction-->Time to Live
        
        Account account = null;
        LoadingCache<String, Account> loadingCache = CacheBuilder.newBuilder().recordStats()
                .maximumWeight(1000).weigher(new Weigher<String, Account>() {
                    @Override
                    public int weigh(String key, Account value) {
                        return value.getRoles().size();
                    }
                }).expireAfterWrite(10, TimeUnit.MINUTES) // or expireAfterAccess() //
                .removalListener(removalNotification -> {
                    Account account1 = removalNotification.getValue();
                    // TODO
                }).refreshAfterWrite(1, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Account>() {
                    @Override
                    public Account load(String s) throws Exception {
                        return null;
                    }
    
                    @Override
                    public Map<String, Account> loadAll(Iterable<? extends String> keys) throws Exception {
                        return super.loadAll(keys); // for CacheLoader.loadAll() usage
                    }

                    @Override
                    public ListenableFuture<Account> reload(final String key, Account account) {
                        if (true) {
                            return Futures.immediateFuture(account);
                        } else {
                            ListenableFutureTask<Account> task = ListenableFutureTask.create(() -> null);
                            executor.execute(task);
                            return task;
                        }
                    }
                });
        // ....
        cacheStats = loadingCache.stats();
        try {
            account = loadingCache.get("aid");
            ImmutableMap<String, Account> accountList = loadingCache.getAll(Lists.newArrayList("", ""));
        } catch (ExecutionException e) {
            e.printStackTrace();
            throw new Exception(e.getCause());
        }
        /** if load() does not declare any checked exception then use getUnchecked() method **/
        // account = loadingCache.getUnchecked("aid");

        Cache<String, Account> loadingCache1 = CacheBuilder.newBuilder().maximumSize(1000).build();
        // ....
        try {
            account = loadingCache1.get("aid", () -> null); // it ia a Callable function
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // inserted directly
        loadingCache1.put("aid1", account);
        
        ConcurrentMap<String, Account> asMap = loadingCache1.asMap();
        asMap.putIfAbsent("ccc", account);

        // explicit removals
        loadingCache.invalidate("");
        loadingCache.invalidateAll(Lists.newArrayList("", ""));

    }
}
