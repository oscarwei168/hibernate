package tw.com.oscar.guava.optional;

import com.google.common.base.Optional;

import java.util.Set;

/**
 * Created by oscarwei168 on 2015/6/11.
 */
public class OptionalTest {

    public static void main(String[] args) {

        Optional<String> optional0 = Optional.absent();
        System.out.println(optional0); // Optional.absent()

        Optional<String> optional1 = Optional.of("Oscar");
        System.out.println(optional1.get()); //  Oscar
        Set<String> set = optional1.asSet();
        System.out.println(set.size()); // 1
        System.out.println(optional1.transform(s -> s.length())); // a function Optional.of(5)

        Optional<String> optional2 = Optional.fromNullable(null);
        System.out.println(optional2.isPresent()); // false
        System.out.println(optional2.or(() -> "0")); // a supplier 0
        System.out.println(optional2.orNull()); // null
        System.out.println(optional2.or("Wei")); // Wei


    }
}
