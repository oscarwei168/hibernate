/**
 * Synchronizer.java
 * Title: DTS Project
 * Copyright: Copyright(c)2016, Acer
 *
 * @author Oscar Wei
 * @since 2016/2/10
 * <p>
 * H i s t o r y
 * 2016/2/10 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.jdk.thread;

/**
 * <p>
 * Title: Synchronizer.java<br>
 * </p>
 * <strong>Description:</strong> this version of the Lock class makes no different guarantees with respect
 * to fairness
 * than synchronized version of doSynchronized() <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2016<br>
 * </p>
 * <p>
 * Company: Acer Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2016/2/10
 * @since 2016/2/10
 */
public class Synchronizer {

    // Lock lock = new Lock();
    FairLock lock = new FairLock();

    public void doSynchronized() throws InterruptedException {
        try {
            this.lock.lock();
            // critical section, do a lot of work which takes a long time
        } finally {
            this.lock.unlock();
        }
    }

}
