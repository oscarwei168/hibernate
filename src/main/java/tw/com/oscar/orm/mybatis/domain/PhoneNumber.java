/**
 * PhoneNumber.java
 * Title: Oscar Wei Java Project
 * Copyright: Copyright(c)2016, oscarwei168
 *
 * @author Oscar Wei
 * @since 2016/8/14
 * <p>
 * H i s t o r y
 * 2016/8/14 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.orm.mybatis.domain;

/**
 * <p>
 * Title: PhoneNumber.java<br>
 * </p>
 * <strong>Description:</strong> //TODO <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2016<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2016/8/14
 * @since 2016/8/14
 */
public class PhoneNumber {

    private String countryCode;
    private String stateCode;
    private String number;

    public PhoneNumber() {
    }

    public PhoneNumber(String countryCode, String stateCode, String
            number) {
        this.countryCode = countryCode;
        this.stateCode = stateCode;
        this.number = number;
    }

    public PhoneNumber(String string) {
        if (string != null) {
            String[] parts = string.split("-");
            if (parts.length > 0) this.countryCode = parts[0];
            if (parts.length > 1) this.stateCode = parts[1];
            if (parts.length > 2) this.number = parts[2];
        }
    }

    public String getAsString() {
        return countryCode + "-" + stateCode + "-" + number;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
