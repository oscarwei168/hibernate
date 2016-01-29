/**
 * RxTest.java
 * Title: DTS Project
 * Copyright: Copyright(c)2016, Acer
 *
 * @author Oscar Wei
 * @since 2016/1/15
 * <p>
 * H i s t o r y
 * 2016/1/15 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.rx;

import rx.Observable;

/**
 * <p>
 * Title: RxTest.java<br>
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
 * @version v1, 2016/1/15
 * @since 2016/1/15
 */
public class RxTest {

    public static void main(String[] args) {
        hello("Oscar", "Amy", "Sunny");
    }

    private static void hello(String... names) {
        Observable.from(names).subscribe(s -> {
            System.out.println(String.format("Hello %s!", s));
        });
    }
}
