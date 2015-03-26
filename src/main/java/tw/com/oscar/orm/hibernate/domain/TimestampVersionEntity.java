/**
 *
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: TimestampVersionEntity
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

import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.util.Date;

/**
 * <strong>Description:</strong><br>
 * This function include: - TODO <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/3/15
 * @since 2015/3/15
 */
@MappedSuperclass
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class TimestampVersionEntity extends BaseEntity {

    private Date version;

    @Version
    @Column(name = "VERSION")
    public Date getVersion() {
        return version;
    }

    private void setVersion(Date version) {
        this.version = version;
    }
}
