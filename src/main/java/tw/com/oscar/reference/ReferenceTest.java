/**
 * ReferenceTest.java
 * Title: DTS Project
 * Copyright: Copyright(c)2016, Acer
 *
 * @author Oscar Wei
 * @since 2016/1/1
 * <p>
 * H i s t o r y
 * 2016/1/1 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * <p>
 * Title: ReferenceTest.java<br>
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
 * @version v1, 2016/1/1
 * @since 2016/1/1
 */
public class ReferenceTest {

    static ReferenceQueue<MyObject> queue = null;

    public static void main(String[] args) {
        StringBuffer buffer1 = new StringBuffer(10); // final reference
        StringBuffer buffer2 = buffer1;

        MyObject obj = new MyObject();
        queue = new ReferenceQueue<>();
        SoftReference<MyObject> softRef = new SoftReference<>(obj, queue);
        // WeakReference<MyObject> weakRef = new WeakReference<>(obj, queue);
        // PhantomReference<MyObject> phantomRef = new PhantomReference<>(obj, queue);

        new CheckRefQueue().start();
        obj = null;
        System.gc();
        System.out.println("After GC:Soft Get= " + softRef.get());
        byte[] b = new byte[4 * 1024 * 925]; // allocate 4M memory
        System.out.println("After new byte[]:Soft Get= " + softRef.get());

    }

    private static class MyObject {

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("MyObject.finalize() called...");
        }

        @Override
        public String toString() {
            return "I am MyObject";
        }
    }

    public static class CheckRefQueue extends Thread {

        @SuppressWarnings("unchecked")
        @Override
        public void run() {
            while (true) {
                if (queue != null) {
                    Reference<MyObject> obj = null;
                    try {
                        obj = (Reference<MyObject>) queue.remove();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (obj != null) {
                        System.out.println("Object for reference is " + obj.get());
                    }
                }
            }
        }
    }
}
