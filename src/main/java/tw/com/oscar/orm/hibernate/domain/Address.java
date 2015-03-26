/**
 *
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: Address
 *
 * @author Oscar Wei
 * @since 2015/3/13
 *
 * H i s t o r y
 *
 * 2015/3/13 Oscar Wei v1
 * + File created 
 */
package tw.com.oscar.orm.hibernate.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * <strong>Description:</strong><br>
 * This function include: - A Address entity <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/3/13
 * @since 2015/3/13
 */
@Entity
@Table(name = "ADDRESS")
public class Address extends BaseEntity {

    private String city;
    private String street;
    private String zipCode;

    public Address() {
    }

    @NotNull
    @Column(name = "CITY", nullable = false)
    @Size(max = 20)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Size(max = 100)
    @Column(name = "STREET")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Size(max = 5)
    @Column(name = "ZIP_CODE")
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        if (street != null ? !street.equals(address.street) : address.street != null) return false;
        if (zipCode != null ? !zipCode.equals(address.zipCode) : address.zipCode != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = city != null ? city.hashCode() : 0;
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
        return result;
    }
}
