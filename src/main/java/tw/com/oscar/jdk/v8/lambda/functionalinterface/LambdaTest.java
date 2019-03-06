/**
 * LambdaTest.java
 * Title: Oscar Wei Java Project
 * Copyright: Copyright(c)2019, oscarwei168
 *
 * @author Oscar Wei
 * @since 2019-01-17
 * <p>
 * H i s t o r y
 * 2019-01-17 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.jdk.v8.lambda.functionalinterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * <p>
 * Title: LambdaTest.java<br>
 * </p>
 * <strong>Description:</strong> //TODO <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2019<br>
 * </p>
 * <p>
 * Company: oscarwei168 Inc.
 * </p>
 *
 * @author Oscar Wei
 * @since 2019-01-17
 * @version v1, 2019-01-17
 */
public class LambdaTest {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(LambdaTest.class);
    
    public static void main(String[] args) {
        // reduce->歸類,類似min/max/sum
        int sum = Stream.of(1, 2, 3, 4).reduce(0, (total, element) -> total + element);
        LOGGER.info(String.format("SUM: %s", sum));
        
        int min = Stream.of(1, 2, 3, 4).min(Comparator.comparing(e -> e)).get();
        LOGGER.info(String.format("MIN: %s", min));
        
        int max = Stream.of(1, 2, 3, 4).max(Comparator.comparing(e -> e)).get();
        LOGGER.info(String.format("MAX: %s", max));
    
        Consumer<String> c = s -> System.out.println(s);
        Consumer<String> mr = System.out::println; // Method reference
        
        IntStream intObject = Stream.of(1, 2, 3, 4).mapToInt(e -> Integer.valueOf(e));
        LongStream longStream = Stream.of(1, 2, 3, 4).mapToLong(Long::valueOf);
        Stream<Long> longBoxed = longStream.boxed();
        IntSummaryStatistics intSummaryStatistics = intObject.summaryStatistics();
        System.out.printf("Max: %d, Min: %d, Count: %d, Avg: %f", intSummaryStatistics.getMax(),
                intSummaryStatistics.getMin(),
                intSummaryStatistics.getCount(), intSummaryStatistics.getAverage());
    
    }
}
