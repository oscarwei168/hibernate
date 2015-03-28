/**
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: User
 *
 * @author Oscar Wei
 * @since 2015/3/16
 * <p>
 * H i s t o r y
 * <p>
 * 2015/3/16 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.orm.hibernate.domain;

import javax.persistence.*;

/**
 * <strong>Description:</strong><br>
 * This function include: - TODO <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/3/16
 * @since 2015/3/16
 */
@Entity
public class User {

    @EmbeddedId
    private UserId pid;
    private int age;

    public User() {
    }

    public UserId getPid() {
        return pid;
    }

    public void setPid(UserId pid) {
        this.pid = pid;
    }

    @Column(name = "AGE")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
