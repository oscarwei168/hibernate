/**
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: InnerClassStyle
 *
 * @author Oscar Wei
 * @since 2015/4/23
 * <p>
 * H i s t o r y
 * <p>
 * 2015/4/23 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.jdk.v8.bytecode;

import java.util.Objects;
import java.util.function.Function;

/**
 * <strong>Description:</strong><br>
 * This function include: - TODO <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/4/23
 * @since 2015/4/23
 */
public class InnerClassStyle {

    Function<String, Integer> f = new Function<String, Integer>() {

        @Override
        public Integer apply(String s) {
            Objects.requireNonNull(s);
            return s.length();
        }
    };
}
