package tw.com.oscar.orm.hibernate.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

/**
 * Created by Oscar on 2015/2/23.
 */
@MappedSuperclass
@Access(AccessType.PROPERTY)
public class NamedEntity extends BaseEntity {

    private String name;

    public NamedEntity() {
    }

    public NamedEntity(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "NAME", nullable = false, length = 50)
    @NotEmpty
    @Size(max = 50)
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.getName();
    }

}
