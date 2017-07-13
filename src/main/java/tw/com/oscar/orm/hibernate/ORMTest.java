/**
 * ORMTest.java
 * Title: Oscar Wei Java Project
 * Copyright: Copyright(c)2016, oscarwei168
 *
 * @author Oscar Wei
 * @since 2016/10/17
 * <p>
 * H i s t o r y
 * 2016/10/17 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.orm.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.stat.EntityStatistics;
import org.hibernate.stat.Statistics;
import org.jboss.logging.Logger;
import tw.com.oscar.orm.hibernate.domain.Course;
import tw.com.oscar.orm.hibernate.domain.Role;
import tw.com.oscar.orm.hibernate.util.HibernateUtil;

/**
 * <p>
 * Title: ORMTest.java<br>
 * </p>
 * <strong>Description:</strong> //TODO <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2016<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2016/10/17
 * @since 2016/10/17
 */
public class ORMTest {
    
    private static final Logger LOGGER = Logger.getLogger(HibernateTest.class);
    
    public static void main(String[] args) {
        
        Statistics statistics = null;
        Transaction tx = null;
        try {
            statistics = HibernateUtil.getSessionFactory().getStatistics();
            statistics.setStatisticsEnabled(true);
            statistics.clear();
            
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            
            Role role = (Role) session.load(Role.class, new Long(1));
            LOGGER.info("" + session.contains(role));
            
            if (!tx.wasCommitted()) {
                tx.commit();
            }
            EntityStatistics statics = statistics.getEntityStatistics(Course.class.getName());
            LOGGER.info("InsertCount : " + statics.toString());
        } catch (Exception e) {
            e.printStackTrace();
            if (!tx.wasRolledBack()) {
                tx.rollback();
            }
        }
    }
}
