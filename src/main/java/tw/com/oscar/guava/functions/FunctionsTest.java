/**
 * FunctionsTest.java
 * Title: DTS Project
 * Copyright: Copyright(c)2015, Acer
 *
 * @author Oscar Wei
 * @since 2015/12/25
 * <p>
 * H i s t o r y
 * 2015/12/25 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.guava.functions;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * <p>
 * Title: FunctionsTest.java<br>
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
 * @version v1, 2015/12/25
 * @since 2015/12/25
 */
public class FunctionsTest {

    public static void main(String[] args) {

        Function<String, String> reverse = input -> new StringBuilder(input).reverse().toString();
        Function<String, Integer> toLen = input -> input.length();
        /** reverse will be apply first **/
        Function func1 = Functions.compose(toLen, reverse);

        Map<String, String> map = Maps.newHashMap();
        /** a function used to obtaining value from map's key **/
        /** throw exception when no value return **/
        Function<String, String> func2 = Functions.forMap(map);
        Function<String, String> func3 = Functions.forMap(map, "unknown");

    }
}
