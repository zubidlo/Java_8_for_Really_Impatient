package Chapter_1_Exercises;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import static Chapter_1_Exercises.Exercise.println;
import static Chapter_1_Exercises.Exercise.argsToFiles;
import static Chapter_1_Exercises.Exercise.printArgs;

/**
 * Given an array of File objects, sort it so that the directories come before the
 * files, and within each group, elements are sorted by path name. Use a lambda
 * expression, not a Comparator .
 */
public class Exercise4 {

    public final static Comparator<File> dirsBeforeFiles = (first, second) ->
            first.isDirectory() && second.isDirectory() || !first.isDirectory() && !second.isDirectory() ?
                    first.getName().toLowerCase().compareTo(second.getName().toLowerCase())
                    : first.isDirectory() ? -1 : 1;

    public static void main(String[] args) {
        printArgs(args);

        Arrays.stream(argsToFiles(args))
                .filter(File::isDirectory)
                .flatMap(f -> Arrays.stream(f.listFiles()))
                .sorted(dirsBeforeFiles)
                .forEach(a -> println(a));

    }
}
