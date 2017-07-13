/**
 * ThreadTest.java
 * Title: DTS Project
 * Copyright: Copyright(c)2016, Acer
 *
 * @author Oscar Wei
 * @since 2016/2/5
 * <p>
 * H i s t o r y
 * 2016/2/5 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.jdk.thread;

/**
 * <p>
 * Title: ThreadTest.java<br>
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
 * @version v1, 2016/2/5
 * @since 2016/2/5
 */
public class ThreadTest {
    
    public static void main(String[] args) throws InterruptedException {

        System.out.println(Thread.currentThread().getName());

        Thread thread1 = new Thread("Oscar") {

            @Override
            public void run() {
                System.out.println(String.format("Thread '%s' running...", Thread.currentThread().getId()));
            }
        };
        thread1.start();

        Thread thread2 = new Thread(() -> {
            System.out.println(String.format("Thread '%s' running...", Thread.currentThread().getId()));
        }, "Amy");
        thread2.start();
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
    }
}
