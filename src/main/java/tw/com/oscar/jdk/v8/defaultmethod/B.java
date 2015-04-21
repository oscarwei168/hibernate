package tw.com.oscar.jdk.v8.defaultmethod;

/**
 * <strong>Description:</strong><br>
 * This function include: - TODO <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/4/17
 * @since 2015/4/17
 */
public interface B extends A {

    default void hello() {
        System.out.println("Hello, B...");
    }
}
