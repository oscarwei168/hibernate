/**
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: OutsourcingCompany
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

import java.io.Serializable;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * <strong>Description:</strong><br>
 * This function include: - TODO <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/4/15
 * @since 2015/4/15
 */
public class OutsourcingCompany implements Serializable {

    private String name;
    private Person boss;
    private Optional<Person> assistant = Optional.ofNullable(null);
    private Stream<Person> employee = Stream.empty();

    public OutsourcingCompany(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getBoss() {
        return boss;
    }

    public void setBoss(Person boss) {
        this.boss = boss;
    }

    public Optional<Person> getAssistant() {
        return assistant;
    }

    public void setAssistant(Optional<Person> assistant) {
        this.assistant = assistant;
    }

    public Stream<Person> getEmployee() {
        return employee;
    }

    public void setEmployee(Stream<Person> employee) {
        this.employee = employee;
    }
}
