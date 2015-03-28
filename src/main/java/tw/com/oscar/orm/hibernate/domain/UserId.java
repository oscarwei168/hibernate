/**
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: UserId
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

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * <strong>Description:</strong><br>
 * This function include: - TODO <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/3/16
 * @since 2015/3/16
 */
@Embeddable
@Access(AccessType.PROPERTY)
public class UserId implements Serializable {

    private String firstName;
    private String lastName;

    public UserId() {
    }

    public UserId(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserId userId = (UserId) o;

        if (firstName != null ? !firstName.equals(userId.firstName) : userId.firstName != null)
            return false;
        if (lastName != null ? !lastName.equals(userId.lastName) : userId.lastName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
