/**
 * LockTest.java
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
package tw.com.oscar.jdk.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <p>
 * Title: LockTest.java<br>
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
public class LockTest {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        lock.lock();
        // lock.lockInterruptibly();
        // lock.tryLock();
        // lock.tryLock(10, TimeUnit.SECONDS);
        // critical section
        lock.unlock();

        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        readWriteLock.readLock().lock();
        // multiple readers can enter this section
        // if not locked for writing, and not writers waiting
        // to lock for writing.
        readWriteLock.readLock().unlock();

        readWriteLock.writeLock().lock();
        // only one writer can enter this section,
        // and only if no threads are currently reading.
        readWriteLock.writeLock().unlock();
    }
}
