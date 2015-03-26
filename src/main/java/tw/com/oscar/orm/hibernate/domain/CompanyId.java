/**
 *
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: CompanyId
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

import org.hibernate.validator.constraints.Length;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * <strong>Description:</strong><br>
 * This function include: - TODO <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/3/15
 * @since 2015/3/15
 */
@Embeddable
@Access(AccessType.PROPERTY)
public class CompanyId implements Serializable {

    private String region;
    private String code;

    public CompanyId() {
    }

    public CompanyId(String region, String code) {
        this.region = region;
        this.code = code;
    }

    @Column(name = "REGION", nullable = false, length = 10)
    @NotNull
    @Size(max = 10)
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Column(name = "CODE", nullable = false, length = 10)
    @NotNull
    @Length(max = 10)
    @Size(max = 10)
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

        CompanyId companyId = (CompanyId) o;

        if (code != null ? !code.equals(companyId.code) : companyId.code != null) return false;
        if (region != null ? !region.equals(companyId.region) : companyId.region != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = region != null ? region.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        return result;
    }
}
