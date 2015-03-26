package tw.com.oscar;

/**
 * @author Oscar Amy
 * @since 2014/12/23.
 */
public class Test {

    public static void main(String[] args) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println();

        }
        try {
            String str = "Oscar".concat("Amy");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
