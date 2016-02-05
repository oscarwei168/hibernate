/**
 * SharedResourcesTest.java
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
 * Title: SharedResourcesTest.java<br>
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
public class SharedResourcesTest {

    /**
     * not thread safe
     **/
    StringBuilder builder = new StringBuilder();

    public void someMethod() {
        /** thread safe because primitive variables store in the thread's local stack **/
        long threadSafeInt = 0;
        threadSafeInt++;
    }

    public void someMethod1() {
        /** local object reference store in the heap **/
        LocalObject localObject = new LocalObject();
        localObject.callMethod();
        anotherMethod(localObject);
    }

    private void anotherMethod(LocalObject localObject) {

    }

    static class LocalObject {
        public void callMethod() {
        }
    }
}
