/**
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: HibernateTest
 *
 * @author Oscar Wei
 * @since 2015/3/14
 * <p>
 * H i s t o r y
 * <p>
 * 2015/3/14 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.orm.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.stat.EntityStatistics;
import org.hibernate.stat.Statistics;
import org.jboss.logging.Logger;
import tw.com.oscar.orm.hibernate.domain.Course;
import tw.com.oscar.orm.hibernate.domain.Student;
import tw.com.oscar.orm.hibernate.util.HibernateUtil;

import java.util.Date;

/**
 * <strong>Description:</strong><br>
 * This function include: - TODO <br/>
 *
 * @author Oscar Wei
 * @version v1, 2015/3/14
 * @since 2015/3/14
 */
public class CourseTest {

    private static final Logger LOGGER = Logger.getLogger(CourseTest.class);
    private static final String ADMIN = "DTS.Admin";

    public static void main(String[] args) {

        Statistics statistics = null;
        try {
            statistics = HibernateUtil.getSessionFactory().getStatistics();
            statistics.setStatisticsEnabled(true);
            statistics.clear();

            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = session.beginTransaction();
            Long pid = test1(session);
            tx.commit();
            EntityStatistics statistic = statistics.getEntityStatistics(Course.class.getName());
            LOGGER.info("InsertCount : " + statistic.getInsertCount());
            statistics.clear();

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            test2(session, pid);
            tx.commit();
            statistic = statistics.getEntityStatistics(Course.class.getName());
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

    private static Long test1(Session session) {
        Course course = new Course();
        course.setName("JAVA");
        course.setUserCreated(ADMIN);
        course.setDateCreated(new Date());

        Student student1 = new Student();
        student1.setName("Oscar Wei");
        student1.setUserCreated(ADMIN);
        student1.setDateCreated(new Date());

        Student student2 = new Student();
        student2.setName("Amy");
        student2.setUserCreated(ADMIN);
        student2.setDateCreated(new Date());

        // Shared Reference each other
        course.addStudent(student1);
        course.addStudent(student2);

        return (Long) session.save(course);

    }

    private static void test2(Session session, Long pid) {
        Course course = (Course) session.get(Course.class, pid);
//        session.delete(course); // 開錯課

        Student student = new Student();
        student.setName("oscar");
        Example example = Example.create(student).ignoreCase().enableLike(MatchMode.ANYWHERE);
        Student selectedStudent = (Student) session.createCriteria(Student.class).add(example)
                .uniqueResult();
        course.getStudents().remove(selectedStudent); // 學生退選

        session.delete(selectedStudent); // 學生休學

    }

}
