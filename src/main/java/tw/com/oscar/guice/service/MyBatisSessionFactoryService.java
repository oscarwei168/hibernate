package tw.com.oscar.guice.service;

import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * <p>
 * Title: MyBatisSessionFactoryService
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
public interface MyBatisSessionFactoryService {

    SqlSessionFactory getSqlSessionFactory(String resource) throws IOException;

    SqlSessionFactory getSqlSessionFactory(Reader reader);

    SqlSessionFactory getSqlSessionFactory(InputStream inputStream);
}
