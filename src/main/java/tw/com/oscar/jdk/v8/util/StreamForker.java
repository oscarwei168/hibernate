/**
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: StreamForker
 *
 * @author Oscar Wei
 * @since 2015/4/11
 * <p>
 * H i s t o r y
 * <p>
 * 2015/4/11 Oscar Wei v1
 * + File created
 */
package tw.com.oscar.jdk.v8.util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * <strong>Description:</strong><br>
 * This function include: - TODO <br>
 *
 * @author Oscar Wei
 * @version v1, 2015/4/11
 * @since 2015/4/11
 */
public class StreamForker<T> {

    private final Stream<T> stream;
    private final Map<Object, Function<Stream<T>, ?>> forks = new HashMap<>();

    public StreamForker(Stream<T> stream) {
        this.stream = stream;
    }

    public StreamForker<T> fork(Object key, Function<Stream<T>, ?> f) {
        forks.put(key, f);
        return this;
    }

//    public Results getResults() {
//        ForkingStreamConsumer<T> consumer = build();
//        try {
//            stream.sequential().forEach(consumer);
//        } finally {
//            consumer.finish();
//        }
//        return consumer;
//    }
//
//    private ForkingStreamConsumer<T> build() {
//        List<BlockingQueue<T>> queues = new ArrayList<>();
//
//        Map<Object, Future<?>> actions = forks.entrySet().stream()
//                .reduce(new HashMap<Object, Future<?>>(), (map, e) ->
//                {
//                    map.put(e.getKey(), getOperationResult(queues, e.getValue()));
//                    return map;
//                }, (m1, m2) -> {
//                    m1.putAll(m2);
//                    return m1;
//                });
//        return null;
//    }
//
//    private Future<?> getOperationResult(List<BlockingQueue<T>> queues, Function<Stream<T>, ?> f) {
//        BlockingQueue queue = new LinkedBlockingDeque<>();
//        queues.add(queue);
//        Spliterator<T> spliterator = new ForkingStreamSpliterator(queue);
//        Stream<T> source = StreamSupport.stream(spliterator, false);
//        return CompletableFuture.supplyAsync(() -> f.apply(source));
//    }
//
//    static class BlockingQueueSpliterator<T> implements Consumer<T>, Results {
//
//        static final Object END_OF_STREAM = new Object();
//        private final List<BlockingQueue<T>> queues;
//        private final Map<Object, Future<?>> actions;
//
//        public BlockingQueueSpliterator(List<BlockingQueue<T>> queues, Map<Object, Future<?>> actions) {
//            this.queues = queues;
//            this.actions = actions;
//        }
//
//
//    }
}
