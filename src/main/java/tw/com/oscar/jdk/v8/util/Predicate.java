package tw.com.oscar.jdk.v8.util;

/**
 * <strong>Description:</strong><br>
 * This function include: - A custom predicate functional interface <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/4/6
 * @since 2015/4/6
 */
//@FunctionalInterface
public interface Predicate<T> {

    boolean testIt(T t);
}
