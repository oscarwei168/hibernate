/**
 * ThrowingProvider.java
 * Title: Oscar Wei Java Project
 * Copyright: Copyright(c)2016, oscarwei168
 *
 * @author Oscar Wei
 * @since 2016/7/8
 * <p>
 * H i s t o r y
 * 2016/7/8 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.generic;

/**
 * <p>
 * Title: ThrowingProvider.java<br>
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
 * @version v1, 2016/7/8
 * @since 2016/7/8
 */
public interface ThrowingProvider<T, E extends Exception> {

    T get() throws E;
}
