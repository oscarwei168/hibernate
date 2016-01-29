/**
 * ObjectsTest.java
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
package tw.com.oscar.guava.objects;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Throwables;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

import java.io.Serializable;

/**
 * <p>
 * Title: ObjectsTest.java<br>
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
public class ObjectsTest {

    public static void main(String[] args) {

        System.out.println(Objects.equal(null, null)); // do not care object contains 'null' property value

        System.out.println(Objects.hashCode("Oscar", "Wei", 25));

        System.out.println(MoreObjects.toStringHelper("ToString").add("name", "Oscar Wei").toString());

        System.out.println(MoreObjects.firstNonNull(null, "2"));
        try {
            String nullValue = MoreObjects.firstNonNull(null, null); // fail fast
            System.out.println(nullValue);
        } catch (Exception e) {
            Throwables.propagate(e);
        }

    }

    static class Entity implements Comparable<Entity>, Serializable {

        private String name;
        private int age;
        private GENTLE gentle = GENTLE.MALE;

        enum GENTLE {
            MALE, FEMALE
        }

        @Override
        public int compareTo(Entity that) {
            return ComparisonChain.start().compare(this.name, that.name).compare(this.age, that.age).compare(this
                    .gentle, that.gentle, Ordering.natural().nullsLast())
                    .result();
        }
    }
}
