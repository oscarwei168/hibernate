/**
 *
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: Customer
 *
 * @author Oscar Wei
 * @since 2015/3/16
 *
 * H i s t o r y
 *
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
public class Customer {

    @EmbeddedId
    private CustomerId pid;

    @MapsId("userId")
    @JoinColumns({ @JoinColumn(name = "FK_USER_FIRST_NAME", referencedColumnName = "firstName"),
            @JoinColumn(name = "FK_USER_LAST_NAME", referencedColumnName = "lastName") })
    @OneToOne
    private User user;

    public Customer() {
    }

    public CustomerId getPid() {
        return pid;
    }

    public void setPid(CustomerId pid) {
        this.pid = pid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
