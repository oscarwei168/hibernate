/**
 * BufferTest.java
 * Title: Oscar Wei Java Project
 * Copyright: Copyright(c)2016, oscarwei168
 *
 * @author Oscar Wei
 * @since 2016/3/20
 * <p>
 * H i s t o r y
 * 2016/3/20 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.vertx;

import io.vertx.core.buffer.Buffer;

/**
 * <p>
 * Title: BufferTest.java<br>
 * </p>
 * <strong>Description:</strong> //TODO <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2016<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2016/3/20
 * @since 2016/3/20
 */
public class BufferTest {

    public static void main(String[] args) {
        // Buffer initialization
        Buffer buffer1 = Buffer.buffer();

        byte[] initialData = new byte[] {1, 2, 3};
        Buffer buffer2 = Buffer.buffer(initialData);

        Buffer buffer3 = Buffer.buffer("initialization");
        Buffer buffer4 = Buffer.buffer("initialization", "UTF-8");

        System.out.println(buffer1.length());

        buffer1.setByte(0, (byte) 127);
        buffer1.setShort(2, (short) 127);
        buffer1.setInt(4, 127);
        buffer1.setLong(8, 127);
        buffer1.setFloat(16, 127.0F);
        buffer1.setDouble(20, 127.0D);
        System.out.println("buffer.length() = " + buffer1.length());

        Buffer buffer = Buffer.buffer();
        System.out.println("buffer.length() = " + buffer.length());
        buffer.appendByte((byte) 127);
        buffer.appendShort((short) 127);
        buffer.appendInt(127);
        buffer.appendLong(127);
        buffer.appendFloat(127.0F);
        buffer.appendDouble(127.0D);
        System.out.println("buffer.length() = " + buffer.length());

        // Read from buffer
        byte aByte = buffer.getByte(0);
        short aShort = buffer.getShort(2);
        int anInt = buffer.getInt(4);
        long aLong = buffer.getLong(8);
        float aFloat = buffer.getFloat(16);
        double aDouble = buffer.getDouble(20);

    }
}
