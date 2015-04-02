/**
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: EmployeeTest
 *
 * @author Oscar Wei
 * @since 2015/3/31
 * <p>
 * H i s t o r y
 * <p>
 * 2015/3/31 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.orm.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.stat.EntityStatistics;
import org.hibernate.stat.Statistics;
import org.jboss.logging.Logger;
import tw.com.oscar.orm.hibernate.domain.Employee;
import tw.com.oscar.orm.hibernate.domain.EmployeePermission;
import tw.com.oscar.orm.hibernate.domain.Permission;
import tw.com.oscar.orm.hibernate.util.HibernateUtil;

import java.util.Date;

/**
 * <strong>Description:</strong><br>
 * This function include: - Many-to-many with extra columns in join table example <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/3/31
 * @since 2015/3/31
 */
public class EmployeeTest {

    private static final Logger LOGGER = Logger.getLogger(CourseTest.class);
    private static final String ADMIN = "DTS.Admin";

    public static void main(String[] args) {
        Statistics statistics = null;
        try {
            statistics = HibernateUtil.getSessionFactory().getStatistics();
            statistics.setStatisticsEnabled(true);
            statistics.clear();

            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//            session.getTransaction().setTimeout(10); // TODO in seconds - it is work
            Transaction tx = session.beginTransaction();
            test1(session);
            tx.commit();
            EntityStatistics statistic = statistics.getEntityStatistics(Employee.class.getName());
            LOGGER.info("InsertCount : " + statistic.getInsertCount());
            statistics.clear();

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
//            test2(session); // TODO not a special case
            tx.commit();
            statistic = statistics.getEntityStatistics(Employee.class.getName());
            LOGGER.info("InsertCount : " + statistic.getInsertCount());
            statistics.clear();

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (null != statistics) {
                statistics.setStatisticsEnabled(false);
            }
//            HibernateUtil.getSessionFactory().close();
            LOGGER.info("Hibernate session is closed");
        }
    }

    private static void test1(Session session) {
        Employee employee = new Employee("E00001", "Oscar Wei");
        employee.setUserCreated(ADMIN);
        employee.setDateCreated(new Date());

        Permission permission = new Permission("ACCOUNT MAINTAIN", true, true);
        permission.setUserCreated(ADMIN);
        permission.setDateCreated(new Date());
        session.save(permission);

        Permission permission1 = new Permission();
        permission1.setName("STORY MAINTAIN");
        permission1.setUserCreated(ADMIN);
        permission1.setDateCreated(new Date());
        session.save(permission1);

        EmployeePermission employeePermission = new EmployeePermission();
        employeePermission.setEmployee(employee);
        employeePermission.setPermission(permission);
        employeePermission.setUserCreated(ADMIN);
        employeePermission.setDateCreated(new Date());

        EmployeePermission employeePermission1 = new EmployeePermission();
        employeePermission1.setEmployee(employee);
        employeePermission1.setPermission(permission1);
        employeePermission1.setUserCreated(ADMIN);
        employeePermission1.setDateCreated(new Date());

        employee.getEmployeePermissions().add(employeePermission);
        employee.getEmployeePermissions().add(employeePermission1);

        session.save(employee);
    }

    private static void test2(Session session) {
        Employee employee = new Employee("E00002", "Amy");
        employee.setUserCreated(ADMIN);
        employee.setDateCreated(new Date());

        Permission permission = (Permission) session.get(Permission.class, 1L);

        EmployeePermission employeePermission = new EmployeePermission();
        employeePermission.setEmployee(employee);
        employeePermission.setPermission(permission);
        employeePermission.setUserCreated(ADMIN);
        employeePermission.setDateCreated(new Date());

        employee.getEmployeePermissions().add(employeePermission);

        session.save(employee);
    }
}
