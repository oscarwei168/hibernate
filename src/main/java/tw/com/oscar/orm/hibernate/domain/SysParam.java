/**
 *
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: SysParam
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

/**
 * <strong>Description:</strong><br>
 * This function include: - TODO <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/3/8
 * @since 2015/3/8
 */

import org.hibernate.annotations.Immutable;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SYS_PARAM")
@Immutable()
public class SysParam extends BaseEntity {

    private String code;

    @Column(name = "CODE")
    @Length(min = 5, max = 10)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SysParam sysParam = (SysParam) o;

        if (code != null ? !code.equals(sysParam.code) : sysParam.code != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }
}
