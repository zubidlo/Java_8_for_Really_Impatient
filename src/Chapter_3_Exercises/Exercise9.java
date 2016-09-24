package Chapter_3_Exercises;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

/**
 * Write a method lexicographicComparator(String... fieldNames) that yields a com-
 * parator that compares the given fields in the given order. For example, a
 * lexicographicComparator("lastname", "firstname") takes two objects and, using
 * reflection, gets the values of the lastname field. If they are different, return the
 * difference, otherwise move on to the firstname field. If all fields match, return 0 .
 */

class Person {

    private final String firstname, lastname;

    Person(final String f, final String l) {
        firstname = f; lastname = l;
    }

    public Optional<String> getFieldValue(final String fieldName) {
        try {
            Field field = getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return Optional.of(field.get(this).toString());
        }
        catch (NoSuchFieldException e) { return Optional.empty(); }
        catch (IllegalAccessException e) { return Optional.empty(); }
    }

    @Override
    public String toString() {
        return firstname + " " + lastname;
    }
}

public class Exercise9 {

    private static Comparator<Person> lexicographicComparator(String... fieldNames) {
        return (firstObject, secondObject) -> {

            Integer result = null;

            for (String fieldName : fieldNames) {
                Optional<String> firstObjectFieldValue = firstObject.getFieldValue(fieldName);
                Optional<String> secondObjectFieldValue = secondObject.getFieldValue(fieldName);

                if (firstObjectFieldValue.isPresent() && !secondObjectFieldValue.isPresent())
                    return -1;

                if (!firstObjectFieldValue.isPresent() && secondObjectFieldValue.isPresent())
                    return 1;

                if (firstObjectFieldValue.isPresent() && secondObjectFieldValue.isPresent()) {

                    int currentFieldComparisonResult =
                            firstObjectFieldValue.get().compareTo(secondObjectFieldValue.get());

                    if (currentFieldComparisonResult == 0) {
                        result = 0;
                        continue;
                    }

                    return currentFieldComparisonResult;
                }
            }

            if (result == null)
                throw new RuntimeException("Person class doesn't have such fields");

            return result;
        };
    }

    public static void main(String[] args) throws InterruptedException {

        Person[] people = {
                new Person("Anto", "Zach"),
                new Person("Anto", "Almo"),
                new Person("Zolo", "Zach"),
                new Person("Zolo", "Almo")
        };

        System.out.println("original list:" + Arrays.asList(people));

        Arrays.sort(people, lexicographicComparator("firstname", "lastname"));
        System.out.println("sorted by firstname and then by lastname:" + Arrays.asList(people));

        Arrays.sort(people, lexicographicComparator("lastname", "firstname"));
        System.out.println("sorted by lastname and then by firstname:" + Arrays.asList(people));

        Arrays.sort(people, lexicographicComparator("lastname", "blabla"));
        System.out.println("sorted by lastname:" + Arrays.asList(people));

        Arrays.sort(people, lexicographicComparator("blabla", "firstname"));
        System.out.println("sorted by firstname:" + Arrays.asList(people));

        Thread.currentThread().sleep(500);
        Arrays.sort(people, lexicographicComparator("blabla", "blabla"));
    }
}
