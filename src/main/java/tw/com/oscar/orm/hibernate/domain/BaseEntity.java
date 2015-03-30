package tw.com.oscar.orm.hibernate.domain;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Oscar on 2015/2/23.
 */
@MappedSuperclass
@Access(AccessType.PROPERTY)
@DynamicInsert
@DynamicUpdate
public class BaseEntity implements Serializable {

    protected Long pid;
    private String userCreated;
    private Date dateCreated;
    private String userLastModified;
    private Date dateLastModified;

    @Id
    @Column(name = "PID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getPid() {
        return pid;
    }

    private void setPid(Long pid) {
        this.pid = pid;
    }

    @Column(name = "USER_CREATED", nullable = false, updatable = false, length = 50)
    @NotNull
    @Size(max = 50)
    public String getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(String userCreated) {
        this.userCreated = userCreated;
    }

    @Column(name = "DATE_CREATED", nullable = false, updatable = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Column(name = "USER_LAST_MODIFIED", insertable = false, length = 50)
    @Size(max = 50)
    public String getUserLastModified() {
        return userLastModified;
    }

    public void setUserLastModified(String userLastModified) {
        this.userLastModified = userLastModified;
    }

    @Column(name = "DATE_LAST_MODIFIED", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateLastModified() {
        return dateLastModified;
    }

    public void setDateLastModified(Date dateLastModified) {
        this.dateLastModified = dateLastModified;
    }

    @Transient
    public boolean isNew() {
        return (null == this.pid);
    }
}
