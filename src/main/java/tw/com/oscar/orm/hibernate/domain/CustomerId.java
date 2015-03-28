/**
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: CustomerId
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
import javax.persistence.Column;
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
public class CustomerId implements Serializable {

    private UserId userId;
    private String no;

    public UserId getUserId() {
        return userId;
    }

    public void setUserId(UserId userId) {
        this.userId = userId;
    }

    @Column(name = "NO", length = 20)
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerId that = (CustomerId) o;

        if (no != null ? !no.equals(that.no) : that.no != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (no != null ? no.hashCode() : 0);
        return result;
    }
}
