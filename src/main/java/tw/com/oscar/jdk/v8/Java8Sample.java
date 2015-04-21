/**
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: Java8Sample
 *
 * @author Oscar Wei
 * @since 2015/4/2
 * <p>
 * H i s t o r y
 * <p>
 * 2015/4/2 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.jdk.v8;

import org.apache.commons.collections4.MapUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tw.com.oscar.jdk.v8.util.Predicate;
import tw.com.oscar.orm.hibernate.domain.Story;
import tw.com.oscar.orm.hibernate.domain.StoryItem;
import tw.com.oscar.orm.hibernate.domain.enums.Priority;
import tw.com.oscar.orm.hibernate.domain.enums.Status;
import tw.com.oscar.orm.hibernate.util.HibernateUtil;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

/**
 * <strong>Description:</strong><br>
 * This function include: - A java 8 new features examples <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/4/2
 * @since 2015/4/2
 */
public class Java8Sample {

    @NotNull
    private static final Logger LOGGER = LoggerFactory.getLogger(Java8Sample.class);
    private static final String ADMIN = "DTS.Admin";

    public static void main(String[] args) {

        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = session.beginTransaction();
            Optional<Story> optional = findStory(session);
            Story story = optional.get();

            // lazy???
            Stream<StoryItem> itemStream = story.getStoryItems().stream();
            Set<StoryItem> items = itemStream.collect(toSet());
            tx.commit();

//            items = filter(items);
//            items = filter(items, Priority.HIGHEST);
//            items = filter(items, Priority.HIGHEST, Status.IN_PROGRESS);

//            Predicate<StoryItem> highest = new Predicate<StoryItem>() {
//
//                @Override
//                public boolean testIt(StoryItem storyItem) {
//                    return Priority.HIGHEST == storyItem.getPriority();
//                }
//            };
//
//            Predicate<StoryItem> inProgress = new Predicate<StoryItem>() {
//
//                @Override
//                public boolean testIt(StoryItem storyItem) {
//                    return Status.IN_PROGRESS == storyItem.getStatus();
//                }
//            };
//            items = filter(items, highest, inProgress);

//            items = filter(items, new Predicate<StoryItem>() {
//
//                @Override
//                public boolean testIt(StoryItem storyItem) {
//                    return Priority.HIGHEST == storyItem.getPriority();
//                }
//            }, new Predicate<StoryItem>() {
//
//                @Override
//                public boolean testIt(StoryItem storyItem) {
//                    return Status.IN_PROGRESS == storyItem.getStatus();
//                }
//            });

            // lambda expression
//            Predicate<StoryItem> condition1 = item -> Priority.HIGHEST == item.getPriority();
//            Predicate<StoryItem> condition2 = item -> Status.IN_PROGRESS == item.getStatus();
//            items = filter(items, condition1, condition2);

            // Stream API Stream<StoryItem>
//            items = items.stream().filter(
//                    item -> Priority.HIGHEST == item.getPriority() && Status.IN_PROGRESS == item.getStatus())
//                    .collect(toSet());
//
//            LOGGER.info("Size : " + items.size());
//            LOGGER.info("Size : " + Objects.toString(items));

            // Stream API enhancement
//            Map<Status, List<StoryItem>> groupingByStatus = grouping(items); // TODO .....
            Map<Status, List<StoryItem>> groupingByStatus = items.stream().collect(groupingBy(StoryItem::getStatus));
            if (MapUtils.isNotEmpty(groupingByStatus)) {
                MapUtils.verbosePrint(System.out, "Map Contents", groupingByStatus);
                groupingByStatus.forEach((k, v) -> System.out.println(k.name() + " : " + v.size()));
            }

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            HibernateUtil.getSessionFactory().close();
            LOGGER.info("Hibernate session is closed");
        }
    }

    @NotNull()
    private static Optional<Story> findStory(Session session) {
        Story story = (Story) session.get(Story.class, 1L);
        Objects.requireNonNull(story);
        return Optional.of(story);
    }

    private static Set<StoryItem> filter(Set<StoryItem> items) {
        // imperative-style programming
        Set<StoryItem> ret = new HashSet<>();
        for (StoryItem item : items) {
            if (Priority.HIGHEST == item.getPriority()) {
                ret.add(item);
            }
        }

        return ret;
    }

//    private static Set<StoryItem> filter(Set<StoryItem> items, Priority priority) {
//        Set<StoryItem> ret = new HashSet<>();
//        for (StoryItem item : items) {
//            if (priority == item.getPriority()) {
//                ret.add(item);
//            }
//        }
//
//        return ret;
//    }

    private static Set<StoryItem> filter(Set<StoryItem> items, Priority priority, Status status) {
        Set<StoryItem> ret = new HashSet<>();
        for (StoryItem item : items) {
            if (priority == item.getPriority() && status == item.getStatus()) {
                ret.add(item);
            }
        }

        return ret;
    }

    private static Set<StoryItem> filter(Set<StoryItem> items, Predicate<StoryItem>... predicates) {
        Set<StoryItem> ret = new HashSet<>();
        // TODO Bed BUT so what
        NEXT:
        for (StoryItem item : items) {
            for (Predicate<StoryItem> predicate : predicates) {
                if (!predicate.testIt(item)) {
                    continue NEXT;
                }
            }
            ret.add(item);
        }

        return ret;
    }

    private static Map<Status, List<StoryItem>> grouping(Set<StoryItem> items) {
        Map<Status, List<StoryItem>> groupByStatus = new HashMap<>();
        for (StoryItem item : items) {
            // TODO shixxxx....GIVE IT UP....
        }
        return groupByStatus;
    }

}
