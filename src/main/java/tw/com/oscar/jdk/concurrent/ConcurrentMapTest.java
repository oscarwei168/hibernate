/**
 * ConcurrentMapTest.java
 * Title: DTS Project
 * Copyright: Copyright(c)2016, Acer
 *
 * @author Oscar Wei
 * @since 2016/2/27
 * <p>
 * H i s t o r y
 * 2016/2/27 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.jdk.concurrent;

import java.util.NavigableSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * <p>
 * Title: ConcurrentMapTest.java<br>
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
 * @version v1, 2016/2/27
 * @since 2016/2/27
 */
public class ConcurrentMapTest {

    public static void main(String[] args) {
        ConcurrentMap<String, String> concurrentMap = new ConcurrentHashMap();
        concurrentMap.put("key", "value");
        String value = concurrentMap.get("key");
        System.out.println(value);

        ConcurrentNavigableMap map = new ConcurrentSkipListMap();
        map.put("1", "one");
        map.put("2", "two");
        map.put("3", "three");
        // strictly less than the given key.
        ConcurrentNavigableMap headMap = map.headMap("2");

        // greater than or equal to the given fromKey
        ConcurrentNavigableMap tailMap = map.tailMap("2");

        // contains all keys from (including), to (excluding) two keys given as parameters to the method[
        ConcurrentNavigableMap subMap = map.subMap("2", "3");

        NavigableSet set = map.descendingKeySet();
        ConcurrentNavigableMap map1 = map.descendingMap();
        NavigableSet set1 = map.navigableKeySet();
    }
}
