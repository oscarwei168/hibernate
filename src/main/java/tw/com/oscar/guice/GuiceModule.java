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
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import org.hibernate.SessionFactory;
import org.jboss.logging.Logger;
import tw.com.oscar.guice.service.MyBatisSessionFactoryService;
import tw.com.oscar.guice.service.impl.MyBatisSessionFactoryServiceImpl;

import java.util.Random;

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

    private static final Logger LOGGER = Logger.getLogger(GuiceModule.class);

    @Override
    protected void configure() {

        bind(MyBatisSessionFactoryService.class).to(MyBatisSessionFactoryServiceImpl.class);
        bind(Random.class).toInstance(new Random());
        /** Providers binding **/
        bind(SessionFactory.class).toProvider(HibernateSessionFactoryProvider.class).asEagerSingleton();
        /** Instances binding **/
        bind(String.class).annotatedWith(Names.named("author")).toInstance("Oscar Wei");
        bind(Integer.class).annotatedWith(Names.named("age")).toInstance(30);
    }

    /**
     * <p>
     * Title: GCSDGuiceModule.java
     * </p>
     * <strong>Description:</strong> A google guice provider for obtaining hibernate SessionFactory object<br>
     * This function include: - <br>
     * <p>
     * Copyright: Copyright (c) 2016
     * </P>
     * <p>
     * Company: Acer Inc.
     * </p>
     *
     * @author Oscar Wei
     * @version v1, 2016/6/8
     * @since 2016/6/8
     */
    static class HibernateSessionFactoryProvider
            implements Provider<SessionFactory> {

        private final Random random;
        private final String author;

        @Inject
        public HibernateSessionFactoryProvider(Random random, @Named("author") String author) {
            this.random = random;
            this.author = author;
        }

        /*
         * (non-Javadoc)
         * @see com.google.inject.Provider#get()
         */
        @Override
        public SessionFactory get() {
            LOGGER.info(String.format("Random: %d, Author: %s", this.random.nextInt(10), this.author));
            // HibernateUtil hibernateUtil = HibernateUtil.getInstance();
            // SessionFactory sessionFactory = hibernateUtil.getSessionFactory();
            // return sessionFactory;
            return null;
        }
    }
}
