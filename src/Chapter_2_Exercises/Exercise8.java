package Chapter_2_Exercises;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static Chapter_2_Exercises.Exercise.setOfWords;

/**
 * Write a method public static <T> Stream<T> zip(Stream<T> first, Stream<T> second)
 * that alternates elements from the streams first and second , stopping when
 * one of them runs out of elements.
 */
public class Exercise8 {

    public static <T> Stream<T> zip(final Stream<T> first, final Stream<T> second) {

        return StreamSupport.stream(new Spliterator<T>() {

            private final Spliterator<T> split1 = first.spliterator();
            private final Spliterator<T> split2 = second.spliterator();
            private Spliterator<T> current = split2;

            @Override
            public boolean tryAdvance(Consumer<? super T> action) {
                current = (current == split1) ? split2 : split1;
                return current.tryAdvance(action);
            }

            @Override
            public Spliterator<T> trySplit() { return null; }

            @Override
            public long estimateSize() { return Long.MAX_VALUE; }

            @Override
            public int characteristics() { return 0; }

        }, false);
    }

    public static void main(String[] args) {

        zip(setOfWords.stream(), Stream.generate(() -> "bla"))
                .forEach(System.out::println);
    }
}
