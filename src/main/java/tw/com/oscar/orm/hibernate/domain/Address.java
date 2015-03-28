/**
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: Address
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

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * <strong>Description:</strong><br>
 * This function include: - A Address entity <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/3/13
 * @since 2015/3/13
 */
@Embeddable
public class Address implements Serializable {

    private String city;
    private String street;
    private String zipCode;

    public Address() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

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
        return !(zipCode != null ? !zipCode.equals(address.zipCode) : address.zipCode != null);

    }

    @Override
    public int hashCode() {
        int result = city != null ? city.hashCode() : 0;
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
        return result;
    }
}
