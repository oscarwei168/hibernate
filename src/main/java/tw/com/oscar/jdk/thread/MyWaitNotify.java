/**
 * MyWaitNotify.java
 * Title: DTS Project
 * Copyright: Copyright(c)2016, Acer
 *
 * @author Oscar Wei
 * @since 2016/2/7
 * <p>
 * H i s t o r y
 * 2016/2/7 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.jdk.thread;

/**
 * <p>
 * Title: MyWaitNotify.java<br>
 * </p>
 * <strong>Description:</strong> A modified version of MySignal class <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2016<br>
 * </p>
 * <p>
 * Company: Acer Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2016/2/7
 * @since 2016/2/7
 */
public class MyWaitNotify {

    MonitorObject monitorObject = new MonitorObject();
    boolean wasSignalled = false; // to avoid 'missed signals'

    public void doWait() {
        synchronized (monitorObject) {
            // if (!wasSignalled) {
            while (!wasSignalled) { // to avoid 'Spurious Wakeup'
                try {
                    // must first obtain the lock on that object
                    // so calling thread must call wait() or notify() from inside a synchronized block
                    monitorObject.wait();
                } catch (InterruptedException e) {
                }
            }
            wasSignalled = false;
        }
    }

    public void doNotify() {
        synchronized (monitorObject) {
            wasSignalled = true;
            // must first obtain the lock on that object
            monitorObject.notify();
        }
    }
}
