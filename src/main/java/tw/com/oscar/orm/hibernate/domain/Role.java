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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;

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
//@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//@Immutable
@NamedQuery(name = Role.SQL_ROLE_FIND_BY_ROLE_NAME, query = "FROM Role r WHERE r.roleName = :roleName")
public class Role extends BaseEntity {

    public static final String SQL_ROLE_FIND_BY_ROLE_NAME = "roleFileByRoleName";

    private String roleName;

    private Set<Account> accounts = new HashSet<>();

    public Role() {
    }

    @Column(name = "ROLE_NAME", nullable = false, length = 45, unique = true)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @ManyToMany(targetEntity = Account.class)
    @JoinTable(name = "ACCOUNT_ROLE", joinColumns = { @JoinColumn(name = "PID_ROLE") },
            inverseJoinColumns = { @JoinColumn(name = "PID_ACCOUNT") })
    @org.hibernate.annotations.ForeignKey(name = "FK_ROLE_ACCOUNT")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}
