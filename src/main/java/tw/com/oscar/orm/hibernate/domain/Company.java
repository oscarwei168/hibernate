/**
 *
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: Company
 *
 * @author Oscar Wei
 * @since 2015/3/15
 *
 * H i s t o r y
 *
 * 2015/3/15 Oscar Wei v1
 * + File created 
 */
package tw.com.oscar.orm.hibernate.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <strong>Description:</strong><br>
 * This function include: - TODO <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/3/15
 * @since 2015/3/15
 */
@Entity
@Table(name = "COMPANY")
public class Company implements Serializable {

    @EmbeddedId
//    @MapsId("userId") // for pk reference another associations usage
//    @AttributeOverride(name = "region", column = @Column(name = "fld_region"))
    private CompanyId pid;
    private String description;

    public Company() {
    }

    public CompanyId getPid() {
        return pid;
    }

    public void setPid(CompanyId pid) {
        this.pid = pid;
    }

    @Column(name = "DESCRIPTION", length = 50)
    @Access(AccessType.PROPERTY)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (description != null ? !description.equals(company.description) : company.description != null)
            return false;
        if (pid != null ? !pid.equals(company.pid) : company.pid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pid != null ? pid.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
