/**
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: ToDo
 *
 * @author Oscar Wei
 * @since 2015/3/13
 * <p>
 * H i s t o r y
 * <p>
 * 2015/3/13 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.orm.hibernate.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Proxy;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.Date;

/**
 * <strong>Description:</strong><br>
 * This function include: - A To Do entity <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/3/13
 * @since 2015/3/13
 */
@Entity
@Table(name = "TODO")
//@Indexed
//@Proxy(lazy = true) // ???
public class ToDo extends BaseEntity {

    private String subject;
    private String description;
    private Date startDate;

    private Account account;

    public ToDo() {
    }

    public ToDo(String subject, String description, Date startDate) {
        this.subject = subject;
        this.description = description;
        this.startDate = startDate;
    }

    @Column(name = "SUBJECT", nullable = false, length = 100)
    @Field
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Column(name = "DESCRIPTION", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PID_ACCOUNT", nullable = false)
    @org.hibernate.annotations.ForeignKey(name = "FK_ACCOUNT_TODO_PID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ToDo toDo = (ToDo) o;

        if (description != null ? !description.equals(toDo.description) : toDo.description != null)
            return false;
        if (startDate != null ? !startDate.equals(toDo.startDate) : toDo.startDate != null)
            return false;
        if (subject != null ? !subject.equals(toDo.subject) : toDo.subject != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = subject != null ? subject.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        return result;
    }
}
