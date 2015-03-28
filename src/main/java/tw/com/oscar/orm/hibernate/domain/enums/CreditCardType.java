package tw.com.oscar.orm.hibernate.domain.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * <strong>Description:</strong><br>
 * This function include: - TODO <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/3/25
 * @since 2015/3/25
 */
public enum CreditCardType {

    VISA("V"), MASTER("M");

    private String code;

    private CreditCardType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static CreditCardType genCreditCardEnum(String code) {
        CreditCardType enumVal = null;
        if (StringUtils.isNotBlank(code)) {
            switch (code) {
                case "V":
                    enumVal = CreditCardType.VISA;
                    break;

                case "M":
                    enumVal = CreditCardType.MASTER;
                    break;

                default:
                    throw new IllegalArgumentException("Wrong credit card code...");
            }
        }
        return enumVal;
    }
}
