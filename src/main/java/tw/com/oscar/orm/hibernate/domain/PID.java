/**
 *
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: PidEntity
 *
 * @author Oscar Wei
 * @since 2015/3/16
 *
 * H i s t o r y
 *
 * 2015/3/16 Oscar Wei v1
 * + File created 
 */
package tw.com.oscar.orm.hibernate.domain;

import javax.persistence.*;

/**
 * <strong>Description:</strong><br>
 * This function include: - TODO <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/3/16
 * @since 2015/3/16
 */
@MappedSuperclass
@Access(AccessType.PROPERTY)
class PID {

    protected Long pid;

    @Id
    @Column(name = "PID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getPid() {
        return pid;
    }

    private void setPid(Long pid) {
        this.pid = pid;
    }
}
