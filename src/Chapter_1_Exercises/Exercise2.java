package Chapter_1_Exercises;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

import static Chapter_1_Exercises.Exercise.println;
import static Chapter_1_Exercises.Exercise.argsToFiles;
import static Chapter_1_Exercises.Exercise.printArgs;

/**
 * Using the listFiles(FileFilter) and isDirectory methods of the java.io.File class,
 * write a method that returns all subdirectories of a given directory. Use a
 * lambda expression instead of a FileFilter object. Repeat with a method
 * expression.
 */
public class Exercise2 {

    public static void main(String[] args) {
        printArgs(args);

        for (File file : argsToFiles(args)) {

            if (file.isDirectory()) {

                println("subdirectories of " + file.getAbsolutePath() + "=");
                File[] subDirs = file.listFiles(File::isDirectory);
                Arrays.sort(subDirs, Comparator.comparing(File::getName));

                for (File directory : subDirs)
                    println(directory.getName().split(System.getProperty("file.separator"))[0]);

                println("");
            }
        }
    }
}