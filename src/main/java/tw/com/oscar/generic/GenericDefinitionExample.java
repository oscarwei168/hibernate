/**
 * GenericDefinitionExample.java
 * Title: DTS Project
 * Copyright: Copyright(c)2015, Acer
 *
 * @author Oscar Wei
 * @since 2015/11/27
 * <p>
 * H i s t o r y
 * 2015/11/27 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.generic;

import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * Title: GenericDefinitionExample.java<br>
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
 * @version v1, 2015/11/27
 * @since 2015/11/27
 */
public abstract class GenericDefinitionExample<T> {

    public abstract T get();

    public abstract <E extends T> List<E> greatestOf(Iterable<E> iterable, int k);

    public abstract <E extends T> List<E> greatestOf(Iterator<E> iterable, int k);

}
