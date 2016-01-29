package tw.com.oscar.guava.precondition;

import com.google.common.collect.Lists;

import java.util.List;

import static com.google.common.base.Preconditions.*;

/**
 * Created by oscarwei168 on 2015/6/11.
 */
public class PreconditionsTest {

    public static void main(String[] args) {

        try {
            int var = -3;
            checkArgument(var > 0, "Illegal argument passed: Negative value = [%s]", var); // only allows %s
        } catch (Exception e) {
            // throw IllegalArgumentException
            System.err.println(e.getMessage());
        }

        try {
            List<String> list = Lists.newArrayList("erer", "dfdf", "sdggr", "aff");
            checkState(list.size() >= 10, "Illegal state passed: [%s] less than [%s]", list.size(), 10);
        } catch (Exception e) {
            // throw IllegalStateException
            System.err.println(e.getMessage());
        }

        try {
            checkNotNull(null, "Illegal argument passed: NULL");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        try {
            int[] index = {1, 2, 3, 4};
            // 'exclusive' second argument
            System.out.println(checkElementIndex(5, index.length, "Illegal argument passed: Invalid index"));
        } catch (Exception e) {
            // check weather first argument is in the index range
            System.err.println(e.getMessage());
        }

        try {
            int[] index = {1, 2, 3, 4};
            checkPositionIndex(5, index.length); // 'inclusive' second argument
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        try {
            int[] index = {1, 2, 3, 4};
            checkPositionIndexes(2, 5, index.length);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}
