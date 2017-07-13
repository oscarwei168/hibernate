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

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.*;
import org.apache.commons.lang.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

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

        /** Maps examples **/
        Map<Integer, String> map1 = Maps.newHashMap();
        ImmutableMap<String, Integer> map = ImmutableMap.of("one", 1, "two", 2);
        Map<String, Integer> salary1 = ImmutableMap.<String, Integer>builder()
                .put("xxx", 1).put("yyy", 2).build();
    
        Map<String, Integer> asMap = Maps.asMap(colors2, new Function<String, Integer>() {
            @Override
            public Integer apply(String input) {
                return input.length();
            }
        });
    
        /** SortedMap **/
        ImmutableSortedMap<String, Integer> salary2 = new ImmutableSortedMap
                .Builder<String, Integer>(Ordering.natural())
                .put("xxx", 100).put("yyy", 200).build();
        assertEquals("xxx", salary2.firstKey());
        assertEquals(2000, salary2.lastEntry().getValue().intValue());
    
        /** BiMap **/
        BiMap<String, Integer> biMap = HashBiMap.create();
        biMap.put("First", 1);
        biMap.put("Second", 2);
        biMap.put("Third", 3);
        assertEquals(2, biMap.get("Second").intValue());
        assertEquals("Third", biMap.inverse().get(3));
    
        /** Multimap **/
        Multimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("fruit", "apple");
        multimap.put("fruit", "banana");
        multimap.put("pet", "cat");
        multimap.put("pet", "dog");
        assertThat(multimap.get("fruit"), containsInAnyOrder("apple", "banana"));
    
    
        /** Table **/
        /** Need more than one key to index a value **/
        Table<String, String, Integer> distance = HashBasedTable.create();
        distance.put("London", "Paris", 340);
        distance.put("New York", "Los Angeles", 3940);
        distance.put("London", "New York", 5576);
        assertEquals(3940, distance.get("New York", "Los Angeles").intValue());
        assertThat(distance.columnKeySet(),
                containsInAnyOrder("Paris", "New York", "Los Angeles"));
        assertThat(distance.rowKeySet(), containsInAnyOrder("London", "New York"));
    
        Table<String, String, Integer> transposed = Tables.transpose(distance);
        assertThat(transposed.rowKeySet(),
                containsInAnyOrder("Paris", "New York", "Los Angeles"));
        assertThat(transposed.columnKeySet(), containsInAnyOrder("London", "New York"));
    
        /** ClassToInstanceMap **/
        ClassToInstanceMap<Number> numbers = MutableClassToInstanceMap.create();
        numbers.putInstance(Integer.class, 1);
        numbers.putInstance(Double.class, 1.5);
        assertEquals(1, numbers.get(Integer.class));
        assertEquals(1.5, numbers.get(Double.class));
    
        /** Group List using Multimap **/
        List<String> names = Lists.newArrayList("John", "Adam", "Tom");
        Function<String, Integer> func = new Function<String, Integer>() {
            public Integer apply(String input) {
                return input.length();
            }
        };
        Multimap<Integer, String> groups = Multimaps.index(names, func);
        assertThat(groups.get(3), containsInAnyOrder("Tom"));
        assertThat(groups.get(4), containsInAnyOrder("John", "Adam"));
    
        Map<String, String> transMap = Maps.transformEntries(map, new Maps.EntryTransformer<String, Integer, String>() {
        
            @Override
            public String transformEntry(String key, Integer value) {
                return key.toLowerCase() +
                        "-" + (value * 100); //new value for this key in transformed map
            }
        });
        System.out.println(transMap);
    
        transMap =
                Maps.transformValues(map, new Function<Integer, String>() {
                
                    @Override
                    public String apply(Integer input) {
                        return "BTC-" + input; //new value in transformed map
                    }
                });
        System.out.println(transMap);

        /** Convert list to map **/
        map1 = Maps.uniqueIndex(colors7, new Function<String, Integer>() {

            @Override
            public Integer apply(String input) {
                return input.length();
            }
        });
    
        /** Convert properties to map **/
        Properties props = new Properties();
        props.setProperty("age", "30");
        Map<String, String> map2 = Maps.fromProperties(props);
    
        /** Filter map by entries **/
        Predicate<Map.Entry<Integer, String>> predicate = new Predicate<Map.Entry<Integer, String>>() {
            @Override
            public boolean apply(Map.Entry<Integer, String> input) {
                return input.getValue().length() > 10;
            }
        };
        Map<Integer, String> midwestStates = Maps.filterEntries(map1, predicate);
    
        /** Filter map by keys **/
        Predicate<Integer> byStateCodeContainsVowelI = new Predicate<Integer>() {
            @Override
            public boolean apply(Integer key) {
                return key > 10;
            }
        };
        Map<Integer, String> stateCodeWithVowelI = Maps.filterKeys(map1, byStateCodeContainsVowelI);
    
        /** Filter map by values **/
        Predicate<String> by15MillionOrGreater = new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return input.length() >= 15000000;
            }
        };
        Map<Integer, String> populationGT15Million = Maps.filterValues(map1, by15MillionOrGreater);
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