/**
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: Role
 *
 * @author Oscar Wei
 * @since 2015/3/9
 * <p>
 * H i s t o r y
 * <p>
 * 2015/3/9 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.orm.hibernate.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * <strong>Description:</strong><br>
 * This function include: - A Role entity <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/3/9
 * @since 2015/3/9
 */
@Entity
@Table(name = "ROLE")
@NamedQuery(name = Role.SQL_ROLE_FIND_BY_ROLE_NAME, query = "FROM Role r WHERE r.roleName = :roleName")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//@Immutable
//@SelectBeforeUpdate
public class Role extends BaseEntity {

    public static final String SQL_ROLE_FIND_BY_ROLE_NAME = "Role.FindByRoleName";

    private String roleName;
    private String description;

    private Set<Account> accounts = new HashSet<>();

    public Role() {
    }

    @Column(name = "ROLE_NAME", nullable = false, length = 45, unique = true)
    @NaturalId
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Column(name = "DESCRIPTION", length = 100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToMany(targetEntity = Account.class)
    @JoinTable(name = "ACCOUNT_ROLE", joinColumns = {@JoinColumn(name = "PID_ROLE")},
            inverseJoinColumns = {@JoinColumn(name = "PID_ACCOUNT")})
    @org.hibernate.annotations.ForeignKey(name = "FK_ROLE_ACCOUNT")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        return !(getRoleName() != null ? !getRoleName().equals(role.getRoleName()) : role.getRoleName() != null);

    }

    @Override
    public int hashCode() {

        return getRoleName() != null ? getRoleName().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleName='" + roleName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
