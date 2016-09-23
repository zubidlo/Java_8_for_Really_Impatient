package Chapter_1_Exercises;

import java.io.File;
import java.util.Arrays;

/**
 * Created by martin on 19/09/16.
 */
public class Exercise {

    static void println(Object o) {
        System.out.println(o);
    }

    static void printArgs(String...args) {
        println("given arguments: " + Arrays.asList(args));
    }

    static void printCurrentThread() {
        println("thread = " + Thread.currentThread().getName());
    }

    static File[] argsToFiles(String...args) {
        File[] files = Arrays.stream(args)
                .map(File::new)
                .filter(File::exists)
                .toArray(File[]::new);
        println("given existing files: " + Arrays.asList(files));
        return files;
    }
}
