/**
 *
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: HibernateUtil
 *
 * @author Oscar Wei
 * @since 2015/3/14
 *
 * H i s t o r y
 *
 * 2015/3/14 Oscar Wei v1
 * + File created 
 */
package tw.com.oscar.orm.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import tw.com.oscar.orm.hibernate.domain.*;

/**
 * <strong>Description:</strong><br>
 * This function include: - TODO <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/3/14
 * @since 2015/3/14
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().setInterceptor(new AuditInterceptor())
                    .addAnnotatedClass(Account.class)
                    .addAnnotatedClass(Role.class).addAnnotatedClass(Address.class)
                    .addAnnotatedClass(ToDo.class).addAnnotatedClass(Credit.class)
                    .addAnnotatedClass(SysParam.class).addAnnotatedClass(Company.class)
                    .addAnnotatedClass(AccountSummary.class).addAnnotatedClass(Customer.class)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory(
                            new StandardServiceRegistryBuilder().build());
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
