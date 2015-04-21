/**
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: Person
 *
 * @author Oscar Wei
 * @since 2015/4/15
 * <p>
 * H i s t o r y
 * <p>
 * 2015/4/15 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.jdk.v8.optional.model;

/**
 * <strong>Description:</strong><br>
 * This function include: - A Person entity <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/4/15
 * @since 2015/4/15
 */
public class Person {

    private String name;
    private String description;

    public Person(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
