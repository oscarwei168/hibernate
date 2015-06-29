/**
 * @(#)MyBatisSessionFactoryServiceImpl Title: Oscar Tutorial Project
 * Copyright: Copyright(c)2015, Oscar Wei Inc.
 * @author Oscar Wei
 * @since 2015/6/27
 * <p>
 * H i s t o r y
 * 2015/6/27 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.guice.service.impl;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import tw.com.oscar.guice.service.MyBatisSessionFactoryService;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * <p>
 * Title: MyBatisSessionFactoryServiceImpl
 * </p>
 * <strong>Description:</strong> //TODO <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Oscar Wei Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/6/27
 * @since 2015/6/27
 */
public class MyBatisSessionFactoryServiceImpl implements MyBatisSessionFactoryService {

    public SqlSessionFactory getSqlSessionFactory(final String resource) throws IOException {
        Reader reader = Resources.getResourceAsReader(resource);
        return new SqlSessionFactoryBuilder().build(reader);
    }

    @Override
    public SqlSessionFactory getSqlSessionFactory(Reader reader) {
        return new SqlSessionFactoryBuilder().build(reader);
    }

    @Override
    public SqlSessionFactory getSqlSessionFactory(InputStream inputStream) {
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
}
