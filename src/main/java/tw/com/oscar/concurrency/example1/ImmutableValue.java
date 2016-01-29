/**
 * ImmutableValue.java
 * Title: DTS Project
 * Copyright: Copyright(c)2016, Acer
 *
 * @author Oscar Wei
 * @since 2016/1/16
 * <p>
 * H i s t o r y
 * 2016/1/16 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.concurrency.example1;

/**
 * <p>
 * Title: ImmutableValue.java<br>
 * </p>
 * <strong>Description:</strong> the ImmutableValue object example <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2016<br>
 * </p>
 * <p>
 * Company: Acer Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2016/1/16
 * @since 2016/1/16
 */
public class ImmutableValue {

    private int value = 0;

    public ImmutableValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public ImmutableValue add(int valueToAdd) {
        return new ImmutableValue(this.value + valueToAdd);
    }
}
