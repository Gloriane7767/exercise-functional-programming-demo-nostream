package com.gloriane;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Consumer;

public class PersonProcessorRefactored {
    public static List<Person> findPeople(
            List<Person> people,
            Predicate<Person> predicate
    ) {
        List<Person> result = new ArrayList<>();
        for (Person person : people) {
            if (predicate.test(person)) {
                result.add(person);
            }
        }
        return result;
    }

    public static void applyToMatching(
            List<Person> people,
            Predicate<Person> predicate,
            Consumer<Person> action
    ) {
        for (Person person : people) {
            if (predicate.test(person)) {
                action.accept(person);
            }
        }
    }
}
/*
Can you give a dummy line by line explanation of this class
Here's a line-by-line explanation of the PersonProcessorRefactored class:
Line 1: Package declaration - places this class in the com.gloriane package
Line 3-5: Import statements - brings in ArrayList, List, Predicate (for testing conditions), and Consumer (for performing actions)
Line 7: Class declaration - defines a public class named PersonProcessorRefactored
Line 8-10: Method signature for findPeople - takes a list of people and a Predicate (a condition to test each person)
Line 12: Creates an empty ArrayList to store matching people
Line 13: Starts a for-each loop to iterate through all people in the input list
Line 14: Tests if the current person matches the condition using predicate.test()
Line 15: If the condition is true, adds that person to the result list
Line 18: Returns the list of people who matched the condition
Line 21-24: Method signature for applyToMatching - takes a list of people, a Predicate to filter them, and a Consumer to perform an action on matches

Line 26: Starts a for-each loop through all people
Line 27: Tests if the current person matches the condition
Line 28: If true, performs the action on that person using action.accept()

The class essentially provides two utility methods: one to filter people based on a condition, and another to perform actions on people who match a condition.
Key differences:
PersonRule → Predicate: Custom interface replaced with Java's built-in functional interface
PersonAction → Consumer: Custom interface replaced with Java's built-in functional interface
action.execute() → action.accept(): Method name changes to match Consumer API
The Predicate/Consumer approach is preferred because it uses standard Java functional interfaces, making the code more
interoperable with Java's Stream API and other libraries.

I do not understand what this means "action.execute() → action.accept(): Method name changes to match Consumer API"
Let me show you the difference with a simple comparison:

PersonAction (custom interface):

@FunctionalInterface
public interface PersonAction {
    void execute(Person person);  // ← method is named "execute"
}

// Usage:
PersonAction action = person -> System.out.println(person.getName());
action.execute(person);  // ← you call "execute"

Copy
java
Consumer (Java's built-in interface):

@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);  // ← method is named "accept"
}

// Usage:
Consumer<Person> action = person -> System.out.println(person.getName());
action.accept(person);  // ← you call "accept"

Copy
java
In your code:

With PersonAction:

public static void applyToMatching(
    List<Person> people,
    PersonRule rule,
    PersonAction action
) {
    for (Person person : people) {
        if (rule.test(person)) {
            action.execute(person);  // ← calling execute()
        }
    }
}

Copy
java
With Consumer:

public static void applyToMatching(
    List<Person> people,
    Predicate<Person> predicate,
    Consumer<Person> action
) {
    for (Person person : people) {
        if (predicate.test(person)) {
            action.accept(person);  // ← calling accept()
        }
    }
}

Copy
java
The only difference is the method name: execute() vs accept(). Both do the same thing - they perform an action on a Person object. Java's Consumer interface just happens to call its method accept instead of execute.
 */