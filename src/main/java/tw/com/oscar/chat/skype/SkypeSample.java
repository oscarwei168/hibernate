/**
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: SkypeSample
 *
 * @author Oscar Wei
 * @since 2015/4/21
 * <p>
 * H i s t o r y
 * <p>
 * 2015/4/21 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.chat.skype;

import com.skype.Skype;

/**
 * <strong>Description:</strong><br>
 * This function include: - TODO <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/4/21
 * @since 2015/4/21
 */
public class SkypeSample {

    public static void main(String[] args) {
        String id = "amy_hung_3";
        System.out.println(id);
        try {
            Skype.chat(id).send("TEST");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
