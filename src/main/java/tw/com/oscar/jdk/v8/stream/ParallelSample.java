/**
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: ParallelSample
 *
 * @author Oscar Wei
 * @since 2015/4/19
 * <p>
 * H i s t o r y
 * <p>
 * 2015/4/19 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.jdk.v8.stream;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * <strong>Description:</strong><br>
 * This function include: - TODO <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/4/19
 * @since 2015/4/19
 */
public class ParallelSample {

    public static void main(String[] args) {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism",
                String.valueOf(Runtime.getRuntime().availableProcessors()));

        System.out.println("Iterative : " + measureSun(ParallelSample::iterativeSum, 10_000_000L) + " msecs");
        System.out.println("Sequential : " + measureSun(ParallelSample::sequentialSum, 10_000_000L) + " msecs");
        System.out.println("Parallel : " + measureSun(ParallelSample::parallelSum, 10_000_000L) + " msecs");
        System.out.println("Range : " + measureSun(ParallelSample::rangeSun, 10_000_000L) + " msecs");
        System.out.println("Parallel Range : " + measureSun(ParallelSample::parallelRangeSun, 10_000_000L) + " msecs");

    }

    public static <T, R> long measureSun(Function<T, R> f, T input) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            R result = f.apply(input);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + result);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }

    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 0; i <= n; i++) {
            result += i;
        }
        return result;
    }

    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(Long::sum).get();
    }

    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(Long::sum).get();
    }

    public static long rangeSun(long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(Long::sum).getAsLong();
    }

    public static long parallelRangeSun(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(Long::sum).getAsLong();
    }
}
