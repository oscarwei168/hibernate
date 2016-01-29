/**
 * CollectionsTest.java
 * Title: DTS Project
 * Copyright: Copyright(c)2015, Acer
 *
 * @author Oscar Wei
 * @since 2015/11/28
 * <p>
 * H i s t o r y
 * 2015/11/28 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.guava.collections;

import com.google.common.base.Optional;
import com.google.common.collect.*;
import org.apache.commons.lang.StringUtils;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * Title: CollectionsTest.java<br>
 * </p>
 * <strong>Description:</strong> //TODO <br>
 * This function include: - <br>
 * <p>
 * Copyright: Copyright (c) 2015<br>
 * </p>
 * <p>
 * Company: Acer Inc.
 * </p>
 *
 * @author Oscar Wei
 * @version v1, 2015/11/28
 * @since 2015/11/28
 */
public class CollectionsTest {

    public static void main(String[] args) {

        ImmutableList<String> colors1 = ImmutableList.of("RED", "ORANGE");

        ImmutableList.Builder<Integer> builder = ImmutableList.builder();
        for (int i = 0; i < 5; i++) {
            builder.add(i);
        }
        ImmutableList<Integer> numbers1 = builder.build();
        List<Integer> number2 = builder.build();

        ImmutableSet<String> colors2 = ImmutableSet.of("RED", "ORANGE", "YELLOW", "GREEN", "BLUE", "PURPLE");
        ImmutableSet<String> colors3 = ImmutableSet.<String>builder().addAll(colors2).add("WHITE").build();

        ImmutableMap<String, Integer> map = ImmutableMap.of("one", 1, "two", 2);

        Foo foo = new Foo(Lists.newArrayList());

        /** Collections2 examples **/
        Collection<Integer> colorLen = Collections2.transform(colors1, input -> input.length());
        Collection<String> filteredColors = Collections2.filter(colors1, e -> e.length() > 1);
        Collection<List<Integer>> permutation1 = Collections2.permutations(Lists.newArrayList(1, 2, 3));
        Collection<List<Integer>> permutation2 = Collections2.orderedPermutations(Lists.newArrayList(1, 2, 3));

        /** Iterables examples **/
        boolean flag1 = Iterables.all(colors2, input -> input.length() > 3);
        boolean flag2 = Iterables.any(colors2, input -> input.length() > 3);
        String color1 = Iterables.find(colors2, e -> StringUtils.equalsIgnoreCase("green", e));
        String color2 = Iterables.find(colors2, e -> StringUtils.equalsIgnoreCase("white", e), "unknown");
        int size = Iterables.frequency(colors2, "BLUE");
        Iterable<Integer> xx = Iterables.cycle(1, 2, 3, 4);
        Iterables.addAll(colors3, Sets.newHashSet("BLACK", "WHITE"));
        Iterable<String> colors4 = Iterables.limit(colors2, 4);
        Iterable<List<String>> colors5 = Iterables.paddedPartition(colors2, 4);
        Iterable<List<String>> colors6 = Iterables.partition(colors2, 4);
        boolean flag3 = Iterables.retainAll(colors2, colors3);
        boolean flag4 = Iterables.removeIf(colors2, e -> e.length() > 6);
        Iterable<String> colors7 = Iterables.skip(colors2, 3);
        String[] colors8 = Iterables.toArray(colors2, String.class);
        Iterable<Integer> xxx = Iterables.transform(colors2, e -> e.length());
        Iterable<String> filtered = Iterables.filter(colors2.asList(), input -> input.length() > 3);
        // Iterables.concat()
        // Iterables.consumingIterable()
        // Iterables.contains()
        // Iterables.elementsEqual()
        // Iterables.indexOf()
        // Iterables.isEmpty()
        // Iterables.mergeSorted()
        Iterables.get(colors2, 1);
        Iterables.get(colors2, 2, "unknown");
        Iterables.getFirst(colors2, "unknown");
        Iterables.getLast(colors2, "unknown");
        // Iterables.getOnlyElement()
        Iterables.unmodifiableIterable(colors5);
        Optional<String> xxxx = Iterables.tryFind(colors2, e -> e.length() > 100);
        // Iterables.getOnlyElement()

        /** Iterators examples - almost same as Iterables class exception following methods **/
        // Iterators.advance()
        // Iterators.asEnumeration()
        // Iterators.peekingIterator()
        // Iterators.singletonIterator()
        // Iterators.getNext()

    }

    static class Foo {

        final ImmutableSet<Bar> bars;
        final ImmutableList<Bar> bars1;

        Foo(List<Bar> bars) {
            this.bars = ImmutableSet.copyOf(bars); // defensive copy
            this.bars1 = ImmutableList.copyOf(bars);
        }
    }

    static class Bar {
    }
}