/**
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: Employee
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
@Table(name = "EMPLOYEE")
public class Employee extends BaseEntity {

    private String code;
    private String name;

    private Set<EmployeePermission> employeePermissions = new HashSet<>(0);

    public Employee() {
    }

    public Employee(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Column(name = "CODE", nullable = false, length = 10)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "NAME", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pid.employee")
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

        Employee employee = (Employee) o;

        if (code != null ? !code.equals(employee.code) : employee.code != null) return false;
        return !(name != null ? !name.equals(employee.name) : employee.name != null);

    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
