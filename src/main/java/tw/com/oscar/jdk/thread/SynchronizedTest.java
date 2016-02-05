/**
 * SynchronizedTest.java
 * Title: DTS Project
 * Copyright: Copyright(c)2016, Acer
 *
 * @author Oscar Wei
 * @since 2016/1/17
 * <p>
 * H i s t o r y
 * 2016/1/17 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.jdk.thread;

/**
 * <p>
 * Title: SynchronizedTest.java<br>
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
 * @version v1, 2016/1/17
 * @since 2016/1/17
 */
public class SynchronizedTest {

    private static int value1;
    // 'volatile' will put variable 'value' to the main memory, not put into the CPU cache
    // and other thread can seeing this variable
    private volatile int value;

    public static synchronized void add3(int valueToTimes) {
        value1 += valueToTimes;
    }

    public static void add4(int valueToAdd) {
        synchronized (SynchronizedTest.class) {
            value1 += valueToAdd;
        }
    }

    public synchronized void add1(int valueToAdd) {
        this.value += valueToAdd;
    }

    public void add2(int valueToAdd) {
        /** in here 'this' is called 'monitor object' **/
        synchronized (this) {
            this.value += valueToAdd;
        }
    }
}
