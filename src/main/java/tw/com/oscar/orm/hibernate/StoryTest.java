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
import org.hibernate.stat.EntityStatistics;
import org.hibernate.stat.Statistics;
import org.jboss.logging.Logger;
import tw.com.oscar.orm.hibernate.domain.Story;
import tw.com.oscar.orm.hibernate.domain.StoryItem;
import tw.com.oscar.orm.hibernate.util.HibernateUtil;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * <strong>Description:</strong><br>
 * This function include: - TODO <br/>
 *
 * @author Oscar Wei
 * @version v1, 2015/3/14
 * @since 2015/3/14
 */
public class StoryTest {

    private static final Logger LOGGER = Logger.getLogger(StoryTest.class);
    private static final String ADMIN = "DTS.Admin";

    public static void main(String[] args) {

        Statistics statistics = null;
        try {
            statistics = HibernateUtil.getSessionFactory().getStatistics();
            statistics.setStatisticsEnabled(true);
            statistics.clear();

            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = session.beginTransaction();
            test(session);
            tx.commit();
            EntityStatistics statistic = statistics.getEntityStatistics(Story.class.getName());
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

    private static void test(Session session) {
        Story story = new Story("Story 1");
        story.setUserCreated(ADMIN);
        story.setDateCreated(new Date());

        StoryItem item1 = new StoryItem("Item 1");
        item1.setUserCreated(ADMIN);
        item1.setDateCreated(new Date());

        StoryItem item2 = new StoryItem("Item 2");
        item2.setUserCreated(ADMIN);
        item2.setDateCreated(new Date());

        StoryItem item3 = new StoryItem("Item 3");
        item3.setUserCreated(ADMIN);
        item3.setDateCreated(new Date());

        // 1:N(U)
        Set<StoryItem> storyItems = new HashSet<>();
        storyItems.add(item1);
        storyItems.add(item2);
        storyItems.add(item3);
        story.setStoryItems(storyItems);
        session.save(story);

        // N:1(U)
//        item1.setStory(story);
//        item2.setStory(story);
//        item3.setStory(story);
//        session.save(item1); // TODO bed solution
//        session.save(item2);
//        session.save(item3);

        // 1:N(B)
//        Set<StoryItem> storyItems = new HashSet<>();
//        item1.setStory(story);
//        item2.setStory(story);
//        item3.setStory(story);
//        storyItems.add(item1);
//        storyItems.add(item2);
//        storyItems.add(item3);
//        story.setStoryItems(storyItems);
//        session.save(story);

    }

}
