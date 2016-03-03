/**
 * CountDownLatchTest.java
 * Title: DTS Project
 * Copyright: Copyright(c)2016, Acer
 *
 * @author Oscar Wei
 * @since 2016/2/27
 * <p>
 * H i s t o r y
 * 2016/2/27 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.jdk.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * <p>
 * Title: CountDownLatchTest.java<br>
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
 * @version v1, 2016/2/27
 * @since 2016/2/27
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);

        Waiter waiter = new Waiter(latch);
        Decrementer decrementer = new Decrementer(latch);

        new Thread(waiter).start();
        new Thread(decrementer).start();

        Thread.sleep(4000);
    }

    static class Waiter implements Runnable {

        CountDownLatch latch = null;

        public Waiter(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Waiter Released");
        }
    }

    static class Decrementer implements Runnable {

        CountDownLatch latch = null;

        public Decrementer(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                this.latch.countDown();

                Thread.sleep(1000);
                this.latch.countDown();

                Thread.sleep(1000);
                this.latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
