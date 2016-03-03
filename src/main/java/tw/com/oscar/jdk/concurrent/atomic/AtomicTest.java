/**
 * AtomicTest.java
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
package tw.com.oscar.jdk.concurrent.atomic;

import java.util.concurrent.atomic.*;

/**
 * <p>
 * Title: AtomicTest.java<br>
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
public class AtomicTest {

    public static void main(String[] args) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        // AtomicBoolean atomicBoolean = new AtomicBoolean();
        boolean value = atomicBoolean.get();
        atomicBoolean.set(false);
        boolean oldValue = atomicBoolean.getAndSet(false);
        boolean expectedValue = true;
        boolean newValue = false;
        boolean wasNewValueSet = atomicBoolean.compareAndSet(expectedValue, newValue);

        AtomicInteger atomicInteger = new AtomicInteger();
        // AtomicInteger atomicInteger = new AtomicInteger(123);
        System.out.println(atomicInteger.getAndAdd(10));
        System.out.println(atomicInteger.addAndGet(-10));
        atomicInteger.decrementAndGet();
        atomicInteger.getAndDecrement();

        AtomicLong atomicLong = new AtomicLong();

        AtomicReference<String> atomicReference = new AtomicReference<>();
        // AtomicReference atomicReference = new AtomicReference();
        String reference = atomicReference.get();
        atomicReference.set("New object referenced");

        String intialRef = null;
        int initialStamp = 0;
        AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference(intialRef, initialStamp);
        String reference1 = atomicStampedReference.getReference();
        int stamp = atomicStampedReference.getStamp();

        int[] stampHolder = new int[1];
        String ref = atomicStampedReference.get(stampHolder);
        System.out.println("ref = " + ref);
        System.out.println("stamp = " + stampHolder[0]);

        String newRef = "New object referenced";
        int newStamp = 1;
        atomicStampedReference.set(newRef, newStamp);

        AtomicIntegerArray arrayInt = new AtomicIntegerArray(10);

        long[] longs = new long[10];
        longs[5] = 123;
        AtomicLongArray arrayLong = new AtomicLongArray(longs);

        AtomicReferenceArray arrayRef = new AtomicReferenceArray(10);
    }
}
