/**
 * @(#)GuiceModule Title: Oscar Tutorial Project
 * Copyright: Copyright(c)2015, Oscar Wei Inc.
 * @author Oscar Wei
 * @since 2015/6/27
 * <p>
 * H i s t o r y
 * 2015/6/27 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.guice;

import com.google.inject.AbstractModule;
import tw.com.oscar.guice.service.MyBatisSessionFactoryService;
import tw.com.oscar.guice.service.impl.MyBatisSessionFactoryServiceImpl;

/**
 * <p>
 * Title: GuiceModule
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
public class GuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MyBatisSessionFactoryService.class).to(MyBatisSessionFactoryServiceImpl.class);
    }
}
