/**
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: Department
 *
 * @author Oscar Wei
 * @since 2015/3/27
 * <p>
 * H i s t o r y
 * <p>
 * 2015/3/27 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.orm.hibernate.domain;

import org.apache.commons.collections4.CollectionUtils;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * <strong>Description:</strong><br>
 * This function include: - TODO <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/3/27
 * @since 2015/3/27
 */
@Entity
@Table(name = "DEPARTMENT")
public class Department extends NamedEntity {

    private Department parentDepartment;
    private Set<Department> subDepartment;

    public Department() {
    }

    public Department(String name) {
        super(name);
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = {})
    @JoinColumn(name = "PID_PARENT_DEPARTMENT")
    public Department getParentDepartment() {
        return parentDepartment;
    }

    protected void setParentDepartment(Department parentDepartment) {
        this.parentDepartment = parentDepartment;
    }

    @OneToMany(mappedBy = "parentDepartment",
            cascade = { CascadeType.PERSIST, CascadeType.MERGE },
            orphanRemoval = true)
//    @JoinTable(name = "ACCOUNT_DEPARTMENT", joinColumns = @JoinColumn(name = "PID_ACCOUNT"),
//            inverseJoinColumns = @JoinColumn(name = "PID_DEPARTMENT"),
//            foreignKey = @ForeignKey(name = "FK_ACCOUNT_DEPARTMENT"),
//            inverseForeignKey = @ForeignKey(name = "FK_DEPARTMENT_ACCOUNT"))
    public Set<Department> getSubDepartment() {
        return subDepartment;
    }

    protected void setSubDepartment(Set<Department> subDepartment) {
        this.subDepartment = subDepartment;
    }

    public void addSubDepartment(Department subDepartment) {
        if (CollectionUtils.isEmpty(this.subDepartment)) {
            this.subDepartment = new LinkedHashSet<>();
        }
        this.getSubDepartment().add(subDepartment);
        subDepartment.setParentDepartment(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        return !(super.getName() != null ? !super.getName().equals(that.getName()) : that
                .getName() != null);

    }

    @Override
    public int hashCode() {
        return super.getName() != null ? super.getName().hashCode() : 0;
    }
}
