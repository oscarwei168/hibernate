package tw.com.oscar.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * ＠author Oscar Wei
 *
 * @since 2014/12/29.
 */
public class BoxDemo {

    public static <U> void addValue(U u, List<Box<U>> boxes) {
        Box<U> box = new Box<U>();
        box.set(u);
        boxes.add(box);
    }

    public static <U> void outputBoxes(List<Box<U>> boxes) {
        for (Box<U> box : boxes) {
            U u = box.getValue();
            System.out.println("" + u.toString());
        }
    }

    public static <U extends Comparable<U>> int countGreaterThan(List<Box<U>> boxes, U u) {
        int counts = 0;
        for (Box<U> box : boxes) {
            if (box.getValue().compareTo(u) > 0) {
                counts++;
            }
        }
        return counts;
    }

    /**
     * @param args List of argument values
     */
    public static void main(String[] args) {
        List<Box<Integer>> boxes = new ArrayList<Box<Integer>>();
        BoxDemo.addValue(10, boxes);
        BoxDemo.addValue(30, boxes);
        BoxDemo.addValue(20, boxes);
        BoxDemo.outputBoxes(boxes);
        System.out.println("Greater than : " + BoxDemo.countGreaterThan(boxes, 15));
    }

}
