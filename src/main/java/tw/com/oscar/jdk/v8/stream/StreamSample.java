/**
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: StreamSample
 *
 * @author Oscar Wei
 * @since 2015/4/8
 * <p>
 * H i s t o r y
 * <p>
 * 2015/4/8 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.jdk.v8.stream;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tw.com.oscar.orm.hibernate.domain.Story;
import tw.com.oscar.orm.hibernate.domain.StoryItem;
import tw.com.oscar.orm.hibernate.util.HibernateUtil;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

/**
 * <strong>Description:</strong><br>
 * This function include: - TODO <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/4/8
 * @since 2015/4/8
 */
public class StreamSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(StreamSample.class);

    public static void main(String[] args) throws Exception {
        Stream<Integer> emptyStream = Stream.empty(); // can be any generic type like String...
        LOGGER.info("Sum : " + emptyStream.mapToInt(d -> 0).sum());

        String str = "Oscar Wei Amy Hung Sunny Wei...";
        Stream<Character> charStream = IntStream.range(0, str.length()).mapToObj(str::charAt);
        charStream.forEach(System.out::println); // normal sequence style
//        charStream.parallel().forEach(System.out::println); // to parallel style(output randomly)

        List<Integer> list1 = asList(1, 2, 3, 4, 5);
        List<Integer> list2 = asList(6, 7, 8, 9, 10);
        Stream<List<Integer>> listStream = Stream.of(list1, list2);
        Stream<Integer> flattenExample = listStream.flatMap(Collection::stream);
        flattenExample.forEach(System.out::println);

        List<int[]> pairs = list1.stream()
                .flatMap(i -> list2.stream().map(j -> new int[]{i, j}))
                .collect(toList());

//        List<int[]> pairs = list1.stream()
//                .flatMap(i -> list2.stream().filter(j -> (i + j) % 3 == 0)
//                        .map(j -> new int[]{i, j})).collect(toList());
//        pairs.forEach(pair -> LOGGER.info(ArrayUtils.toString(pair)));

        long[] xx = LongStream.rangeClosed(1, 10).toArray();

        LOGGER.info("Unique words : " + uniqueWords("src/main/resources/data/data.txt"));

        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);

        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]}).limit(10).map(t ->
                t[0]).forEach(System.out::println);

        Stream<int[]> pythagoreanTriples = pythagoreanTriples();
        pythagoreanTriples.limit(5).forEach(t -> LOGGER.info(t[0] + "," + t[1] + "," +
                t[2]));

        LOGGER.info("Multicore : " + Runtime.getRuntime().availableProcessors());

        try {

            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            Transaction tx = session.beginTransaction();
            Optional<Story> optional = findStory(session);
            Story story = optional.get();
            Stream<StoryItem> itemStream = story.getStoryItems().stream();

            // Obtaining all names that delay 3 days, sorting by delay
            List<StoryItem> items = itemStream.collect(toList());
//            items = over3DayDelay(items);
//            Collections.sort(items, new Comparator<StoryItem>() {
//
//                @Override
//                public int compare(StoryItem item1, StoryItem item2) {
//                    return -Integer.compare(item1.getDelay(), item2.getDelay());
//                }
//            });
//            List<String> itemNames = new ArrayList<>();
//            for (StoryItem item : items) {
//                itemNames.add(item.getName());
//            }
//            LOGGER.info("Result : " + Objects.toString(itemNames));

//            LOGGER.info("Result : " + story.getStoryItems().stream().filter(item -> 3 < item.getDelay())
//                    .sorted(comparing(StoryItem::getDelay).reversed())
//                    .map(StoryItem::getName).limit(3)
//                    .collect(joining(", ")));

            // Match examples
            boolean isAnyMatch = itemStream
                    .anyMatch(item -> 3 < item.getDelay());
            LOGGER.info("isAnyMatch : " + isAnyMatch);
//
//            boolean isAllMatch = itemStream
//                    .allMatch(item -> Status.COMPLETED == item.getStatus());
//            LOGGER.info("isAllMatch : " + isAllMatch);
//
//            boolean isNoneMatch = itemStream
//                    .noneMatch(item -> {
//                        LOGGER.info(item.getName() + " : " + item.getPriority().name());
//                        return Priority.HIGH == item.getPriority();
//                    });
//            LOGGER.info("isNoneMatch : " + isNoneMatch);

//            Optional<StoryItem> findAny = itemStream
//                    .filter(item -> {
//                        System.out.println("Item name : " + item.getName() + ":" + item.getStatus().name());
//                        return Status.TO_DO == item.getStatus();
//                    })
//                    .findAny();
//            StoryItem item = findAny.orElse(new StoryItem());
//            StoryItem item = findAny.orElseGet(() -> new StoryItem("Nothing"));
//            StoryItem item = findAny.orElseThrow(() -> new Exception("Error..."));

//            Optional<StoryItem> findFirst = itemStream.filter(item -> Status.TO_DO == item.getStatus()).findFirst();
//            StoryItem item = findFirst.orElse(new StoryItem());

//            int sum = itemStream.filter(item -> 3 < item.getDelay()).
//                    map(StoryItem::getDelay).reduce(0, (i, j) -> i + j);
//            LOGGER.info("Sum : " + sum);

//            IntSummaryStatistics statistics = itemStream.mapToInt(StoryItem::getDelay).summaryStatistics();
//            LOGGER.info("" + statistics.getMin() + " " + statistics.getMax()
//                    + " " + statistics.getAverage() + " " + statistics.getSum());

            tx.commit();
        } finally {
            HibernateUtil.getSessionFactory().close();
            LOGGER.info("Hibernate session is closed");
        }
    }

    private static Optional<Story> findStory(Session session) {
        Story story = (Story) session.get(Story.class, 1L);
        Objects.requireNonNull(story);
        return Optional.of(story);
    }

    private static List<StoryItem> over3DayDelay(List<StoryItem> items) {
        List<StoryItem> ret = new ArrayList<>();
        for (StoryItem item : items) {
            if (3 < item.getDelay()) {
                ret.add(item);
            }
        }
        return ret;
    }

    // The Pythagorean theorem --> a * a + b * b = c * c
    private static Stream<int[]> pythagoreanTriples() {
        return IntStream.rangeClosed(1, 100).boxed().flatMap(a -> IntStream.rangeClosed(a, 100)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                .filter(t -> t[2] % 1 == 0));
    }

    /**
     * @param filename
     * @return
     */

    private static long uniqueWords(String filename) {
        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get(filename), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" "))).distinct().count();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return uniqueWords;
    }
}
