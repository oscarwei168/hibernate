/**
 * StringsTest.java
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
package tw.com.oscar.guava.strings;

import com.google.common.base.Strings;

/**
 * <p>
 * Title: StringsTest.java<br>
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
public class StringsTest {

    public static void main(String[] args) {

        String s1 = Strings.emptyToNull(null);
        String s2 = Strings.nullToEmpty(null);
        System.out.println(Strings.isNullOrEmpty(s1));
        System.out.println(s2);
    }
}
