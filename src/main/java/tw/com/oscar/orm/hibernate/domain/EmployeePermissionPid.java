/**
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: EmployeePermissionPid
 *
 * @author Oscar Wei
 * @since 2015/3/31
 * <p>
 * H i s t o r y
 * <p>
 * 2015/3/31 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.orm.hibernate.domain;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * <strong>Description:</strong><br>
 * This function include: - TODO <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/3/31
 * @since 2015/3/31
 */
@Embeddable
public class EmployeePermissionPid implements Serializable {

    private Employee employee;
    private Permission permission;

    @ManyToOne
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @ManyToOne
    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeePermissionPid that = (EmployeePermissionPid) o;

        if (employee != null ? !employee.equals(that.employee) : that.employee != null)
            return false;
        return !(permission != null ? !permission.equals(that.permission) : that.permission != null);

    }

    @Override
    public int hashCode() {
        int result = employee != null ? employee.hashCode() : 0;
        result = 31 * result + (permission != null ? permission.hashCode() : 0);
        return result;
    }
}
