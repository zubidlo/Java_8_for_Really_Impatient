package Chapter_1_Exercises;

import java.io.File;
import java.util.Arrays;

import static Chapter_1_Exercises.Exercise.println;
import static Chapter_1_Exercises.Exercise.argsToFiles;
import static Chapter_1_Exercises.Exercise.printArgs;

/**
 * Using the list(FilenameFilter) method of the java.io.File class, write a method
 * that returns all files in a given directory with a given extension. Use a lambda
 * expression, not a FilenameFilter . Which variables from the enclosing scope does
 * it capture?
 */
public class Exercise3 {

    public static void main(String[] args) {
        printArgs(args);

        String extension = args[0];
        println("given extension: " + extension);
        args = Arrays.asList(args).subList(1, args.length).toArray(new String[0]);

        for (File file : argsToFiles(args)) {
            if (file.isDirectory()) {
                println("files with \"*.txt\" extension in: " + file + "=");
                Arrays.asList(file.list((f, name) -> name.endsWith(extension)))
                        .forEach(a -> println(a));
            }
        }
    }
}
