/**
 * PhoneTypeHandler.java
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
package tw.com.oscar.orm.mybatis.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import tw.com.oscar.orm.mybatis.domain.PhoneNumber;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>
 * Title: PhoneTypeHandler.java<br>
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
public class PhoneTypeHandler extends BaseTypeHandler<PhoneNumber> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, PhoneNumber phoneNumber, JdbcType jdbcType) throws
            SQLException {
        ps.setString(i, phoneNumber.getAsString());
    }

    @Override
    public PhoneNumber getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return new PhoneNumber(rs.getString(columnName));
    }

    @Override
    public PhoneNumber getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return new PhoneNumber(rs.getString(columnIndex));
    }

    @Override
    public PhoneNumber getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return new PhoneNumber(cs.getString(columnIndex));
    }
}
