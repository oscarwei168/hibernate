/**
 * CyclicBarrierTest.java
 * Title: DTS Project
 * Copyright: Copyright(c)2016, Acer
 *
 * @author Oscar Wei
 * @since 2016/2/28
 * <p>
 * H i s t o r y
 * 2016/2/28 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.jdk.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * <p>
 * Title: CyclicBarrierTest.java<br>
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
 * @version v1, 2016/2/28
 * @since 2016/2/28
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        Runnable barrier1Action = () -> System.out.println("BarrierAction 1 executed ");
        Runnable barrier2Action = () -> System.out.println("BarrierAction 2 executed ");

        // CyclicBarrier barrier = new CyclicBarrier(2);
        CyclicBarrier barrier1 = new CyclicBarrier(2, barrier1Action);
        CyclicBarrier barrier2 = new CyclicBarrier(2, barrier2Action);

        CyclicBarrierRunnable barrierRunnable1 = new CyclicBarrierRunnable(barrier1, barrier2);

        CyclicBarrierRunnable barrierRunnable2 = new CyclicBarrierRunnable(barrier1, barrier2);

        new Thread(barrierRunnable1).start();
        new Thread(barrierRunnable2).start();
    }

    static class CyclicBarrierRunnable implements Runnable {

        CyclicBarrier barrier1 = null;
        CyclicBarrier barrier2 = null;

        public CyclicBarrierRunnable(CyclicBarrier barrier1, CyclicBarrier barrier2) {
            this.barrier1 = barrier1;
            this.barrier2 = barrier2;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " waiting at barrier 1");
                this.barrier1.await();
                // this.barrier1.await(10, TimeUnit.SECONDS);

                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " waiting at barrier 2");
                this.barrier2.await();

                System.out.println(Thread.currentThread().getName() + " done!");

            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
