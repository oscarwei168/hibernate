/**
 * ExchangerTest.java
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

import java.util.concurrent.Exchanger;

/**
 * <p>
 * Title: ExchangerTest.java<br>
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
public class ExchangerTest {

    public static void main(String[] args) {
        Exchanger exchanger = new Exchanger();

        ExchangerRunnable exchangerRunnable1 = new ExchangerRunnable(exchanger, "A");

        ExchangerRunnable exchangerRunnable2 = new ExchangerRunnable(exchanger, "B");

        new Thread(exchangerRunnable1).start();
        new Thread(exchangerRunnable2).start();
    }

    static class ExchangerRunnable implements Runnable {

        Exchanger exchanger = null;
        Object object = null;

        public ExchangerRunnable(Exchanger exchanger, Object object) {
            this.exchanger = exchanger;
            this.object = object;
        }

        @Override
        public void run() {
            try {
                Object previous = this.object;

                this.object = this.exchanger.exchange(this.object);

                System.out.println(Thread.currentThread().getName() + " exchanged " + previous + " for " + this.object);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
