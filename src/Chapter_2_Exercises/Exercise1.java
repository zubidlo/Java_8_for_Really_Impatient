package Chapter_2_Exercises;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static Chapter_2_Exercises.Exercise.setOfWords;
import static Chapter_2_Exercises.Exercise.longerThanNCharsWithLogging;

/**
 * Write a parallel version of the for loop in Section 2.1, “From Iteration to
 * Stream Operations,” on page 22. Obtain the number of processors. Make that
 * many separate threads, each working on a segment of the list, and total up
 * the results as they come in. (You don’t want the threads to update a single
 * counter. Why?)
 */
class Exercise1 {

    public static void main(String[] args) {

        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println("processors: " + processors);
        int n = 12;

        Map<Integer, List<String>> listChunks = setOfWords.stream()
                .collect(Collectors.groupingBy(w -> setOfWords.indexOf(w) % processors));

        ExecutorService threadPool = Executors.newFixedThreadPool(processors);

        Function<Map.Entry<Integer, List<String>>, Future<Long>> countElementsInChunk = chunk ->
                threadPool.submit(() -> chunk.getValue().stream()
                .filter(longerThanNCharsWithLogging(n, true))
                .count());

        Function<Future<Long>, Long> execFuture = f -> {
            long countOfChunk = 0;
            try { countOfChunk = f.get(); }
            catch (Exception e) { e.printStackTrace(); }
            return countOfChunk;
        };


        long overallCount = listChunks.entrySet().stream()
                .map(countElementsInChunk)
                .map(execFuture)
                .reduce((a, b) -> a + b)
                .orElseThrow(() -> new RuntimeException("something went wrong with a promise."));

        System.out.format("words longer than %d characters counted in parallel: %d%n", n, overallCount);
        threadPool.shutdown();

        // sequential stream version for comparison
        System.out.format("words longer than %d characters counted sequentially: %d%n",
                n, setOfWords.stream()
                .filter(longerThanNCharsWithLogging(n, false))
                .count());
    }
}
