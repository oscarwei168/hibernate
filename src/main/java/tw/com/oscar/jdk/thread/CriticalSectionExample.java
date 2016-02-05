/**
 * CriticalSectionExample.java
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
 * Title: CriticalSectionExample.java<br>
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
public class CriticalSectionExample {

    protected long count = 0;
    private int sum1 = 0;
    private int sum2 = 0;

    /**
     * Critical section block
     **/
    public void add(long value) {
        this.count = this.count + value;
    }

    public void add(int val1, int val2) {
        synchronized (this) {
            this.sum1 += val1;
            this.sum2 += val2;
        }
    }

    public void add1(int val1, int val2) {
        /** two sum variables are independent of each other **/
        synchronized (this) {
            this.sum1 += val1;
        }
        synchronized (this) {
            this.sum2 += val2;
        }
    }
}
