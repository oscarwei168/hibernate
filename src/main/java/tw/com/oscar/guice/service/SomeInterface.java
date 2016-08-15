/**
 * SomeInterface.java
 * Title: Oscar Wei Java Project
 * Copyright: Copyright(c)2016, oscarwei168
 *
 * @author Oscar Wei
 * @since 2016/7/12
 * <p>
 * H i s t o r y
 * 2016/7/12 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.guice.service;

import com.google.inject.ImplementedBy;
import tw.com.oscar.guice.service.impl.SomeInterfaceImpl;

/**
 * <p>
 * Title: SomeInterface.java<br>
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
 * @version v1, 2016/7/12
 * @since 2016/7/12
 */
@ImplementedBy(SomeInterfaceImpl.class)
public interface SomeInterface {

    void print();
}
