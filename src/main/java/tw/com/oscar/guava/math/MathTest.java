/**
 * MathTest.java
 * Title: DTS Project
 * Copyright: Copyright(c)2015, Acer
 *
 * @author Oscar Wei
 * @since 2015/12/30
 * <p>
 * H i s t o r y
 * 2015/12/30 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.guava.math;

import com.google.common.math.IntMath;

import java.math.RoundingMode;

/**
 * <p>
 * Title: MathTest.java<br>
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
 * @version v1, 2015/12/30
 * @since 2015/12/30
 */
public class MathTest {

    public static void main(String[] args) {

        int factorialValue = IntMath.factorial(5);
        int divideValue = IntMath.divide(10, 4, RoundingMode.HALF_UP);
        int addValue = IntMath.checkedAdd(Integer.MAX_VALUE, 1);
        int modValue = IntMath.mod(10, 3);
        int multiplyValue = IntMath.checkedMultiply(100000, 20);
        int subtractValue = IntMath.checkedSubtract(Integer.MAX_VALUE, 1);
    }
}
