package Chapter_3_Exercises;

import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Enhance the lazy logging technique by providing conditional logging. A
 * typical call would be logIf(Level.FINEST, () -> i == 10, () -> "a[10] = " + a[10]) .
 * Don’t evaluate the condition if the logger won’t log the message.
 */
public class Exercise1 {
    private static Logger logger = Logger.getGlobal();

    private static void logIf(Level level, Supplier<Boolean> condition, Supplier<String> msg) {
        if (logger.isLoggable(level) && condition.get()) logger.log(level, msg);
    }

    public static void main(String[] args) {
        logger.setLevel(Level.WARNING);
        logIf(Level.WARNING, () -> true, () -> "hello world"); //this logs
        logIf(Level.WARNING, () -> false, () -> "hello world");
        logIf(Level.INFO, () -> true, () -> "hello world");
    }
}
