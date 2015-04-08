/**
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: HibernateUtil
 *
 * @author Oscar Wei
 * @since 2015/3/14
 * <p>
 * H i s t o r y
 * <p>
 * 2015/3/14 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.orm.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateUtil.class);
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
//            return new Configuration().addAnnotatedClass(Course.class)
//                    .addAnnotatedClass(Student.class).addAnnotatedClass(Story.class)
//                    .addAnnotatedClass(StoryItem.class).addAnnotatedClass(Employee.class)
//                    .addAnnotatedClass(Permission.class).addAnnotatedClass(EmployeePermission.class)
//                    .setInterceptor(new AuditInterceptor()).buildSessionFactory(new
//                            StandardServiceRegistryBuilder().build());

            return new Configuration().configure().setInterceptor(new AuditInterceptor())
                    .addPackage("tw.com.oscar.orm.hibernate.domain")
                    .addAnnotatedClass(Account.class).addAnnotatedClass(Story.class).addAnnotatedClass(StoryItem.class)
                    .addAnnotatedClass(Role.class).addAnnotatedClass(Address.class)
                    .addAnnotatedClass(ToDo.class).addAnnotatedClass(Credit.class)
                    .addAnnotatedClass(Company.class)
                    .addAnnotatedClass(AccountSummary.class).addAnnotatedClass(Customer.class)
                    .addAnnotatedClass(User.class).addAnnotatedClass(Department.class)
                    .buildSessionFactory(
                            new StandardServiceRegistryBuilder().build());
        } catch (Throwable ex) {
            LOGGER.error("Initial SessionFactory creation failed." + ex);
            LOGGER.error(ex.getMessage(), ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
