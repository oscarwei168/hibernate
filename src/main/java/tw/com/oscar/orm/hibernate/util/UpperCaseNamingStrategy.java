/**
 *
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: UpperCaseNamingStrategy
 *
 * @author Oscar Wei
 * @since 2015/3/15
 *
 * H i s t o r y
 *
 * 2015/3/15 Oscar Wei v1
 * + File created 
 */
package tw.com.oscar.orm.hibernate.util;

import org.hibernate.cfg.ImprovedNamingStrategy;

/**
 * <strong>Description:</strong><br>
 * This function include: - TODO <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/3/15
 * @since 2015/3/15
 */
public class UpperCaseNamingStrategy extends ImprovedNamingStrategy {

    @Override
    public String tableName(String tableName) {
        return super.tableName(tableName).toUpperCase();
    }

    @Override
    public String columnName(String columnName) {
        return super.columnName(columnName).toUpperCase();
    }

    @Override
    public String propertyToColumnName(String propertyName) {
        return super.propertyToColumnName(propertyName).toUpperCase();
    }
}
