/**
 * Exchanger.java
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
 * Title: Exchanger.java<br>
 * </p>
 * <strong>Description:</strong> read and write instructions of volatile variables cannot be reordered <br>
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
public class Exchanger {

    private Object object = null;
    private volatile boolean hasNewObject = false;

    public void put(Object newObject) {
        while (hasNewObject) {
            // wait - do not overwrite existing new object
        }
        object = newObject;
        hasNewObject = true; // volatile write so before instructions cannot be reordered
    }

    public Object take() {
        while (!hasNewObject) { // volatile read so after instructions cannot be reordered
            // wait - don't take old object (or null)
        }
        Object obj = object;
        hasNewObject = false; // volatile write
        return obj;
    }

}
