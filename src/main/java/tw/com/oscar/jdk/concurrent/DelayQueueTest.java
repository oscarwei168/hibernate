/**
 * DelayQueueTest.java
 * Title: DTS Project
 * Copyright: Copyright(c)2016, Acer
 *
 * @author Oscar Wei
 * @since 2016/2/14
 * <p>
 * H i s t o r y
 * 2016/2/14 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.jdk.concurrent;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * Title: DelayQueueTest.java<br>
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
 * @version v1, 2016/2/14
 * @since 2016/2/14
 */
public class DelayQueueTest {

    public static void main(String[] args) throws InterruptedException {

        DelayQueue<Delayed> queue = new DelayQueue<>();

        Delayed element = new DelayElement();

        queue.put(element);

        Delayed element1 = queue.take();
    }

    static class DelayElement implements Delayed {

        @Override
        public long getDelay(TimeUnit unit) {
            return 0;
        }

        @Override
        public int compareTo(Delayed o) {
            return 0;
        }
    }
}
