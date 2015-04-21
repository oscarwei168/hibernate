/**
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: OutsourcingStaff
 *
 * @author Oscar Wei
 * @since 2015/4/15
 * <p>
 * H i s t o r y
 * <p>
 * 2015/4/15 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.jdk.v8.optional.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

/**
 * <strong>Description:</strong><br>
 * This function include: - A OutsourcingStaff entity <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/4/15
 * @since 2015/4/15
 */
public class OutsourcingStaff implements Serializable {

    private Optional<OutsourcingCompany> company;
    private String name;

    public OutsourcingStaff(String name) {
        Objects.requireNonNull(name);
        this.name = name;
    }

    public Optional<OutsourcingCompany> getCompany() {
        return company;
    }

    public void setCompany(Optional<OutsourcingCompany> company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
