/**
 * MySignal.java
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
 * Title: MySignal.java<br>
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
 * @version v1, 2016/2/7
 * @since 2016/2/7
 */
public class MySignal {

    protected boolean hasDataToProcess = false;

    public synchronized boolean hasDataToProcess() {
        return this.hasDataToProcess;
    }

    public synchronized void setHasDataToProcess(boolean hasData) {
        this.hasDataToProcess = hasData;
    }

    static class RunnableExample implements Runnable {

        private MySignal signal;

        RunnableExample(MySignal signal) {
            this.signal = signal;
        }

        @Override
        public void run() {
            // it is called 'busy wait' but not very efficient way
            // so use java build-in wait() method
            while (!signal.hasDataToProcess) {
                // do nothing...
            }
            // do something...
        }
    }
}
