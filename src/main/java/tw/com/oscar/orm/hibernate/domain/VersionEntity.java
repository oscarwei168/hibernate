/**
 *
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: VersionEntity
 *
 * @author Oscar Wei
 * @since 2015/3/8
 *
 * H i s t o r y
 *
 * 2015/3/8 Oscar Wei v1
 * + File created 
 */
package tw.com.oscar.orm.hibernate.domain;

import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * <strong>Description:</strong><br>
 * This function include: - TODO <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/3/8
 * @since 2015/3/8
 */
@MappedSuperclass
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class VersionEntity extends BaseEntity {

    private Integer version;

    @Version
    @Column(name = "VERSION")
    public Integer getVersion() {
        return version;
    }

    private void setVersion(Integer version) {
        this.version = version;
    }
}
