package tech.eta.dataloader_d;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class StreamUtils {

    // Helper method that returns the custom ring-buffer collector
//    public static <T> Collector<T, ?, Queue<T>> lastNElements(int n) {
    public static <T> Collector<T, ?, Stream<T>> lastNElements(int n) {
        int N = Math.max(0, n);
        return Collector.of(
                () -> new ArrayDeque<T>(N), // SUPPLIER: mutable container for collecting
                (queue, element) -> {       // ACCUMULATOR: add elements, keep deque at most at N
                    if (queue.size() == N) queue.poll(); // so we round-robbin and never grow the queue
                    queue.add(element);                  // poll on empty dequeue is safe (returns null)
                },
                (q1, q2) -> {       // COMBINER: Merge partial queues for parallel streams
                    if (q2.size() >= N) return q2; // cant ever be larger than N. If full, return that.
                    while (q1.size() + q2.size() > N) q1.poll(); // shave off stale parts of q1
                    q1.addAll(q2);
                    return q1;
                },
                // 4. FINISHER: Optional. Transforms the internal queue directly into a fresh stream
                Collection::stream
        );
    }

    public static void main(String[] args) {
        Stream<String> myStream = Stream.of("A", "B", "C", "D", "E");

        // Clean, readable, and reusable pipeline
//        Stream<String> trimmedStream = myStream.collect(lastNElements(3)).stream(); // if collecting to a queue
        Stream<String> trimmedStream = myStream.collect(lastNElements(3)); // now, collecting to a stream...

        trimmedStream.forEach(System.out::print); // Outputs: CDE
    }
}
