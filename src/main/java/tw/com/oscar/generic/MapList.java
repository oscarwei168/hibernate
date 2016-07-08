/**
 * MapList.java
 * Title: Oscar Wei Java Project
 * Copyright: Copyright(c)2016, oscarwei168
 *
 * @author Oscar Wei
 * @since 2016/4/17
 * <p>
 * H i s t o r y
 * 2016/4/17 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Title: MapList.java<br>
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
 * @version v1, 2016/4/17
 * @since 2016/4/17
 */
public class MapList<T, R> extends ArrayList<R> {

    List<T> list;
    Mappable<T, R> mapper;

    public MapList(List<T> list) {
        super();
        this.list = list;
    }

    @Override
    public R get(int i) {
        return mapper.map(list.get(i));
    }

    public MapList<T, R> map(Mappable<T, R> mapper) {
        this.mapper = mapper;
        return this;
    }

    public interface Mappable<T, R> {
        R map(T t);
    }
}
