/**
 * RegexTest.java
 * Title: DTS Project
 * Copyright: Copyright(c)2016, Acer
 *
 * @author Oscar Wei
 * @since 2016/2/28
 * <p>
 * H i s t o r y
 * 2016/2/28 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.jdk.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Title: RegexTest.java<br>
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
 * @version v1, 2016/2/28
 * @since 2016/2/28
 */
public class RegexTest {

    public static void main(String[] args) {

        //  follows is 'character'
        // [abc]: a or b or c
        // [^abc]: not a or b or c
        // [a-zA-Z]: a to z or A to Z
        // [a-d[m-p]]: a to d or m to p(聯集)
        // [a-z&&[def]]: d or e or f(交集)
        // [a-z&&[^bc]]: equal to [ad-z](差集)
        // [a-z&&[^m-p]]: equal to [a-lq-z](差集)

        String inputStr = "ABC\nABC\nABC";
        String patternStr = "(?d)ABC";
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(inputStr);
        boolean matchFound = matcher.find();
        while (matchFound) {
            System.out.println(matcher.start() + "-" + matcher.end());
            for (int i = 0; i <= matcher.groupCount(); i++) {
                String groupStr = matcher.group(i);
                System.out.println(i + ":" + groupStr);
            }
            if (matcher.end() + 1 <= inputStr.length()) {
                matchFound = matcher.find(matcher.end());
            } else {
                break;
            }
        }
    }
}
