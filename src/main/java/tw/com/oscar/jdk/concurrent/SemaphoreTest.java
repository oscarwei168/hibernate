/**
 * SemaphoreTest.java
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

import java.util.concurrent.Semaphore;

/**
 * <p>
 * Title: SemaphoreTest.java<br>
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
public class SemaphoreTest {

    public static void main(String[] args) throws InterruptedException {
        // Semaphore class is a counting semaphore
        Semaphore semaphore = new Semaphore(1);
        // Semaphore semaphore = new Semaphore(1, true); // Fairness

        // critical section
        semaphore.acquire();
        // ...
        semaphore.release();
    }
}
