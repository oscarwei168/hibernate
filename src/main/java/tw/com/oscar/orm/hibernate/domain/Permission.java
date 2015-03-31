/**
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: Permission
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

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * <strong>Description:</strong><br>
 * This function include: - TODO <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/3/31
 * @since 2015/3/31
 */
@Entity
@Table(name = "PERMISSION")
public class Permission extends BaseEntity {

    private String name;
    private boolean read = false;
    private boolean write = false;

    private Set<EmployeePermission> employeePermissions = new HashSet<>();

    public Permission() {
    }

    public Permission(String name, boolean read, boolean write) {
        this.name = name;
        this.read = read;
        this.write = write;
    }

    @Column(name = "NAME", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "[READ]", nullable = false, columnDefinition = "TINYINT", length = 1)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    @Column(name = "[WRITE]", nullable = false, columnDefinition = "TINYINT", length = 1)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    public boolean isWrite() {
        return write;
    }

    public void setWrite(boolean write) {
        this.write = write;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pid.permission")
    public Set<EmployeePermission> getEmployeePermissions() {
        return employeePermissions;
    }

    public void setEmployeePermissions(Set<EmployeePermission> employeePermissions) {
        this.employeePermissions = employeePermissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Permission that = (Permission) o;

        if (read != that.read) return false;
        if (write != that.write) return false;
        return !(name != null ? !name.equals(that.name) : that.name != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (read ? 1 : 0);
        result = 31 * result + (write ? 1 : 0);
        return result;
    }
}
