/**
 * MonitorTest.java
 * Title: DTS Project
 * Copyright: Copyright(c)2016, Acer
 *
 * @author Oscar Wei
 * @since 2016/1/22
 * <p>
 * H i s t o r y
 * 2016/1/22 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.guava.concurrency;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Monitor;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * Title: MonitorTest.java<br>
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
 * @version v1, 2016/1/22
 * @since 2016/1/22
 */
public class MonitorTest {

    private volatile boolean condition = true;
    private int taskDoneCounter;
    private AtomicInteger taskSkippedCounter = new AtomicInteger(0);

    private List<String> list = Lists.newArrayList();
    private static final int MAX_SIZE = 10;

    private ReentrantLock lock = new ReentrantLock();
    private Condition listAtCapacity = lock.newCondition();

    private Monitor monitor = new Monitor();
    private Monitor.Guard listBelowCapacity = new Monitor.Guard(monitor) {

        @Override
        public boolean isSatisfied() {
            // if over the MAX_SIZE value then thread will be wait...
            return list.size() < MAX_SIZE;
        }
    };

    public void addToList(String item) throws InterruptedException {
        // only one thread can enter monitor block at any time
        // when monitor object don't return value then use as follows
        monitor.enterWhen(listBelowCapacity);
        try {
            list.add(item);
        } finally {
            monitor.leave();
        }
    }

    public void addToListReentrantLockVersion(String item) throws InterruptedException {
        lock.lock();
        try {
            while (list.size() == MAX_SIZE) {
                listAtCapacity.await();
            }
            list.add(item);
        } finally {
            lock.unlock();
        }
    }

    public void removeFromList(String item) {
        // Monitor object best practice
        if (monitor.enterIf(listBelowCapacity, 10, TimeUnit.SECONDS)) {
            try {
                doWork(item);
                taskDoneCounter++;
            } finally {
                monitor.leave();
            }
        } else {
            taskSkippedCounter.incrementAndGet();
        }
    }

    private void doWork(String item) {
        list.remove(item);
    }
}
