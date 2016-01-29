/**
 * RandomAccessFileTest.java
 * Title: DTS Project
 * Copyright: Copyright(c)2015, Acer
 *
 * @author Oscar Wei
 * @since 2015/12/30
 * <p>
 * H i s t o r y
 * 2015/12/30 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * <p>
 * Title: RandomAccessFileTest.java<br>
 * </p>
 * <strong>Description:</strong> //TODO <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015<br>
 * </p>
 * <p>
 * Company: Acer Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/12/30
 * @since 2015/12/30
 */
public class RandomAccessFileTest {

    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("data/nio.txt", "rw");
        FileChannel inChannel = aFile.getChannel();
//        MappedByteBuffer mbb = inChannel.map(FileChannel.MapMode.READ_WRITE, 0, aFile.length());
        ByteBuffer buffer = ByteBuffer.allocate(48);
//        ByteBuffer buffer = ByteBuffer.wrap(new byte[48]);
//        ByteBuffer buffer = ByteBuffer.allocateDirect(48);
//        CharBuffer buffer = CharBuffer.allocate(48);
        int bytesRead = inChannel.read(buffer);
        while (bytesRead != -1) {
            buffer.flip(); // reading or writing mode switch
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get()); // read 1 byte at a time
            }
            buffer.clear();
            bytesRead = inChannel.read(buffer);
        }
        aFile.close();
    }

    public static void copyFile(String source, String target) throws IOException {
        FileInputStream fis = new FileInputStream(source);
        FileOutputStream fos = new FileOutputStream(target);
        FileChannel readChannel = fis.getChannel();
        FileChannel writeChannel = fos.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        System.out.println("Initial buffer --> Limit : " + buffer.limit() + ", Capacity : " + buffer.capacity() +
                ", Position : " +
                buffer.position());
        while (true) {
            buffer.clear();
            int len = readChannel.read(buffer);
            if (-1 == len) {
                break;
            }
//            ByteBuffer duplicateBuffer = buffer.duplicate();
//            ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
//            while (readOnlyBuffer.hasRemaining()) {
//                System.out.print(readOnlyBuffer.get() + " ");
//            }
//            ByteBuffer sliceBuffer = buffer.slice();
            System.out.println("Before read buffer -> Limit : " + buffer.limit() + ", Capacity : " + buffer.capacity()
                    + ", Position : " + buffer.position());
            buffer.flip();
            System.out.println("After flip() -> Limit : " + buffer.limit() + ", Capacity : " + buffer.capacity()
                    + ", Position : " + buffer.position());
            writeChannel.write(buffer);
        }
        readChannel.close();
        writeChannel.close();
    }
}
