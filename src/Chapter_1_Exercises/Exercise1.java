package Chapter_1_Exercises;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

import static Chapter_1_Exercises.Exercise.println;
import static Chapter_1_Exercises.Exercise.argsToFiles;
import static Chapter_1_Exercises.Exercise.printArgs;
import static Chapter_1_Exercises.Exercise.printCurrentThread;

/**
 * Is the comparator code in the Arrays.sort method called in the same thread as
 * the call to sort or a different thread?
 */
public class Exercise1 {

    private static Comparator<File> comparator = (first, second) -> {
        printCurrentThread();
        println("comparing: " + first.getName() + " with: " + second.getName());

        return first.isDirectory() && second.isDirectory() || !first.isDirectory() && !second.isDirectory() ?
                first.getName().toLowerCase().compareTo(second.getName().toLowerCase())
                : first.isDirectory() ? -1 : 1;
    };

    public static void main(String[] args) {
        printCurrentThread();
        printArgs(args);
        File[] files;

        files = Arrays.stream(argsToFiles(args))
                .filter(File::isDirectory)
                .flatMap(f -> Arrays.stream(f.listFiles()))
                .toArray(File[]::new);

        Arrays.sort(files, comparator);
        println(Arrays.asList(files));
    }
}
