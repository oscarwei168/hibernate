package tw.com.oscar.generic;

/**
 * Created by Oscar on 2014/12/29.
 */
public class Box<T> {

    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }
}
