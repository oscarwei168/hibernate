/**
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: EmployeePermission
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

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <strong>Description:</strong><br>
 * This function include: - TODO <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/3/31
 * @since 2015/3/31
 */
@Entity
@Table(name = "EMPLOYEE_PERMISSION")
@AssociationOverrides({
        @AssociationOverride(name = "pid.employee",
                joinColumns = @JoinColumn(name = "PID_EMPLOYEE")),
        @AssociationOverride(name = "pid.permission",
                joinColumns = @JoinColumn(name = "PID_PERMISSION")) })
public class EmployeePermission implements Serializable {

    private EmployeePermissionPid pid = new EmployeePermissionPid();
    private String userCreated;
    private Date dateCreated;

    public EmployeePermission() {
    }

    @EmbeddedId
    public EmployeePermissionPid getPid() {
        return pid;
    }

    public void setPid(EmployeePermissionPid pid) {
        this.pid = pid;
    }

    @Transient
    public Employee getEmployee() {
        return this.getPid().getEmployee();
    }

    public void setEmployee(Employee employee) {
        this.getPid().setEmployee(employee);
    }

    @Transient
    public Permission getPermission() {
        return this.getPid().getPermission();
    }

    public void setPermission(Permission permission) {
        this.getPid().setPermission(permission);
    }

    @Column(name = "USER_CREATED", nullable = false, length = 20)
    public String getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(String userCreated) {
        this.userCreated = userCreated;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_CREATED", nullable = false)
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        EmployeePermission that = (EmployeePermission) o;

        return !(getPid() != null ? !getPid().equals(that.getPid())
                : that.getPid() != null);

    }

    public int hashCode() {
        return (getPid() != null ? getPid().hashCode() : 0);
    }
}
