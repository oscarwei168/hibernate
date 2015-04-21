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
import org.hibernate.stat.EntityStatistics;
import org.hibernate.stat.Statistics;
import org.jboss.logging.Logger;
import tw.com.oscar.orm.hibernate.domain.Story;
import tw.com.oscar.orm.hibernate.domain.StoryItem;
import tw.com.oscar.orm.hibernate.util.HibernateUtil;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

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

            // 1:N(U and B) example
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = session.beginTransaction();
            test1(session);
            tx.commit();
            EntityStatistics statistic = statistics.getEntityStatistics(Story.class.getName());
            LOGGER.info("InsertCount : " + statistic.getInsertCount());
            statistics.clear();

            // get() and load() example
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            HibernateUtil.getSessionFactory().getCache().evictEntity(Story.class, 1L);
            test2(session);
            tx.commit();

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            HibernateUtil.getSessionFactory().getCache().evictEntity(Story.class, 1L);
            prepareData(session);
            tx.commit();

            // FetchMode examples
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            HibernateUtil.getSessionFactory().getCache().evictEntity(Story.class, 1L);
            test3(session);
            tx.commit();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (null != statistics) {
                statistics.setStatisticsEnabled(false);
            }
            HibernateUtil.getSessionFactory().close();
            LOGGER.info("Hibernate session is closed");
        }

    }

    private static void test1(Session session) {
        Story story = new Story("Story 0");
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
//        Set<StoryItem> storyItems = new HashSet<>();
//        storyItems.add(item1);
//        storyItems.add(item2);
//        storyItems.add(item3);
//        story.setStoryItems(storyItems);
//        session.save(story); // TODO Good BUT not good enough

        // N:1(U)
//        item1.setStory(story);
//        item2.setStory(story);
//        item3.setStory(story);
//        session.save(item1); // TODO Bed solution
//        session.save(item2);
//        session.save(item3);

        // 1:N(B)
        Set<StoryItem> storyItems = new HashSet<>();
        item1.setStory(story);
        item2.setStory(story);
        item3.setStory(story);
        storyItems.add(item1);
        storyItems.add(item2);
        storyItems.add(item3);
        story.setStoryItems(storyItems);
        session.save(story); // TODO Good solution

    }

    private static void test2(Session session) throws Exception {
        Story story = (Story) session.load(Story.class, 1L); // TODO get() vs load()
        if (null != story) {
            Stream<StoryItem> storyItems = story.getStoryItems().stream();
            LOGGER.info("Items : " + storyItems
                    .sorted((item1, item2) -> item1.getPriority().compareTo(item2.getPriority()))
                    .map(StoryItem::getName).distinct()
                    .collect(joining(", ")));
        }

    }

    private static void test3(Session session) {
        Example example = Example.create(new Story()).excludeZeroes().ignoreCase().excludeProperty("status");
        List<Story> storys = session.createCriteria(Story.class).add(example).list();

        for (Story story : storys) {
            LOGGER.info(story.getName());
            Set<StoryItem> items = story.getStoryItems();
            for (Iterator<StoryItem> iters = items.iterator(); iters.hasNext(); ) {
                StoryItem item = iters.next();
                LOGGER.info(item.getName());
            }

        }
    }

    private static void prepareData(Session session) {

        for (int i = 1; i <= 11; i++) {
            Story story = new Story("Story " + i);
            story.setUserCreated(ADMIN);
            story.setDateCreated(new Date());

            for (int j = 1; j <= 2; j++) {
                StoryItem item = new StoryItem("Item " + i + j);
                item.setUserCreated(ADMIN);
                item.setDateCreated(new Date());
                add(story, item);

            }

            session.save(story);
        }

    }

    private static void add(Story story, StoryItem storyItem) {
        if (null == story.getStoryItems()) {
            story.setStoryItems(new HashSet<>());
        }
        story.getStoryItems().add(storyItem);
        storyItem.setStory(story);
    }

}
