package tw.com.oscar.jdk.v8.lambda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tw.com.oscar.orm.hibernate.domain.Story;
import tw.com.oscar.orm.hibernate.domain.StoryItem;
import tw.com.oscar.orm.hibernate.domain.enums.Priority;
import tw.com.oscar.orm.hibernate.domain.enums.Status;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.*;

/**
 * The Java 8 lambda expression samples
 *
 * @author Oscar Wei
 * @since 2015/1/20
 */
public class LambdaSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(LambdaSample.class);

    public static void main(String[] args) {

        // Example 1 - lambda expression
        Runnable aThread = () -> LOGGER.info("Lambda expression");
        aThread.run();
        Callable<Integer> aCallable = () -> 20;
        Comparator<StoryItem> aComparator =
                (StoryItem item1, StoryItem item2)
                        -> item1.getPriority().compareTo(item2.getPriority());
        Button aButton = new Button("Click");
        ActionListener listener = event -> {
            aButton.setLabel("Login"); // TODO no 'final' keyword???
        };
        aButton.addActionListener(listener);
//        button = new Button(""); // TODO failure

        // Example 2 - functional interfaces
        Predicate<StoryItem> predicate1 = item -> Priority.HIGHEST == item.getPriority();
        Predicate<Set<StoryItem>> predicate2 = items -> items.isEmpty();
        Function<StoryItem, Integer> aFunction1 = item -> item.getName().length();
        Consumer<String> aConsumer1 = s -> LOGGER.info(s);
        aConsumer1.accept("Oscar love Java 8");
        Supplier<String> aSupplier = () -> new String("Supplier...");
        String aString = aSupplier.get();
        LOGGER.info(aString);
        BiFunction<Integer, Integer, Integer> aBiFunction1 = (x, y) -> x + y;
        LOGGER.info("Sum : " + aBiFunction1.apply(2, 3));
        BiFunction<String, Integer, String> aBiFunction2 = (x, y) -> x + y;
        LOGGER.info(aBiFunction2.apply("Result : ", 3));
        IntBinaryOperator xx = (x, y) -> x + y;
        LOGGER.info("Sum : " + xx.applyAsInt(4, 6));
        BinaryOperator<Integer> aBinaryOperator = (x, y) -> x * y;
        LOGGER.info("Multiply : " + aBinaryOperator.apply(2, 3));

        // Example 3 - method/constructor references
        Consumer<String> aConsumer2 = LOGGER::info;
        Predicate<Set<StoryItem>> predicate3 = Set::isEmpty;

        Supplier<Story> supplier = Story::new;
        Story aStory1 = supplier.get();

        Function<String, Story> aFunction2 = Story::new;
        Story aStory2 = aFunction2.apply("Story 1");

        BiFunction<String, Status, Story> aBiFunction3 = Story::new;
        Story aStory3 = aBiFunction3.apply("Story 2", Status.NEW);

        // Example 4 - API examples
        Comparator<StoryItem> comparator =
                (item1, item2) -> item1.getPriority().compareTo(item2.getPriority());
        comparator = comparator.reversed().thenComparing(StoryItem::getDelay);

        Predicate<StoryItem> predicate = item -> Priority.HIGHEST == item.getPriority();
        Predicate<StoryItem> condition =
                predicate.negate().and(item -> 3 < item.getDelay())
                        .or(item -> Status.TO_DO == item.getStatus());

        Function<Integer, Integer> a = i -> i + 1;
        Function<Integer, Integer> b = i -> i * 3;
        Function<Integer, Integer> c = a.andThen(b);
        Function<Integer, Integer> d = a.compose(b);
        int calculate1 = c.apply(2);
        LOGGER.info("Calculate : " + calculate1);
        int calculate2 = d.apply(2);
        LOGGER.info("Calculate : " + calculate2);

        // function with exception example
        Function<BufferedReader, String> f = reader -> {
            try {
                return reader.readLine();
            } catch (IOException ioe) {
                throw new RuntimeException(ioe);
            }
        };

        ExecutorService pool = Executors.newFixedThreadPool(1, r -> new Thread(r, "Oscar Thread")); // ThreadFactory
        Future future = pool.submit(aCallable);
        pool.shutdown();
        LOGGER.info("" + future.isDone());

    }

    private static double integrate(DoubleFunction<Double> f, double a, double b) {
        return (f.apply(a) + f.apply(b)) * (b - a) / 2.0;
    }

}
