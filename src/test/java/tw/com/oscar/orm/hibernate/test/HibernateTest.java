/**
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: HibernateTest
 *
 * @author Oscar Wei
 * @since 2015/3/28
 * <p>
 * H i s t o r y
 * <p>
 * 2015/3/28 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.orm.hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.stat.Statistics;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tw.com.oscar.orm.hibernate.domain.*;
import tw.com.oscar.orm.hibernate.util.AuditInterceptor;

/**
 * <strong>Description:</strong><br>
 * This function include: - TODO <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/3/28
 * @since 2015/3/28
 */
public class HibernateTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateTest.class);

    //    private ApplicationContext factory;
    private Statistics statistics = null;
    private Session session = null;

    @Before
    public void setUp() {
//        factory = new ClassPathXmlApplicationContext("applicationContext.xml");
        statistics = getSessionFactory().getStatistics();
        statistics.setStatisticsEnabled(true);
        openSession();
    }

    @After
    public void tearDown() {
        statistics.setStatisticsEnabled(false);
        closeSession();
    }

    private void openSession() {
//        SessionFactory sessionFactory = getSessionFactory();
//        this.session = SessionFactoryUtils.getSession(sessionFactory, true);
//        TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session));
    }

    private void closeSession() {
//        SessionFactory sessionFactory = getSessionFactory();
//        SessionHolder sessionHolder = (SessionHolder) TransactionSynchronizationManager.unbindResource(sessionFactory);
//        sessionHolder.getSession().flush();
//        sessionHolder.getSession().close();
//        SessionFactoryUtils.releaseSession(sessionHolder.getSession(), sessionFactory);
    }

    private void restartSession() {
        closeSession();
        openSession();
    }

    private SessionFactory getSessionFactory() {
        return buildSessionFactory();
    }

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().setInterceptor(new AuditInterceptor())
                    .addAnnotatedClass(Account.class)
                    .addAnnotatedClass(Role.class).addAnnotatedClass(Address.class)
                    .addAnnotatedClass(ToDo.class).addAnnotatedClass(Credit.class)
                    .addAnnotatedClass(Company.class)
                    .addAnnotatedClass(AccountSummary.class).addAnnotatedClass(Customer.class)
                    .addAnnotatedClass(User.class).addAnnotatedClass(Department.class)
                    .buildSessionFactory(
                            new StandardServiceRegistryBuilder().build());
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }
}
