/**
 * CloneUtils.java
 * Title: Oscar Wei Java Project
 * Copyright: Copyright(c)2017, oscarwei168
 *
 * @author Oscar Wei
 * @since 2017/11/9
 * <p>
 * H i s t o r y
 * 2017/11/9 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.generic;

import java.io.*;

/**
 * <p>
 * Title: CloneUtils.java<br>
 * </p>
 * <strong>Description:</strong> //TODO <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2017<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2017/11/9
 * @since 2017/11/9
 */
public class CloneUtils {

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T cloneThroughSerialize(T t) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        serializeToOutputStream(t, bos);
        byte[] bytes = bos.toByteArray();
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
        return (T) ois.readObject();
    }

    private static void serializeToOutputStream(Serializable ser, OutputStream os)
            throws IOException {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(os);
            oos.writeObject(ser);
            oos.flush();
        } finally {
            oos.close();
        }
    }
}
