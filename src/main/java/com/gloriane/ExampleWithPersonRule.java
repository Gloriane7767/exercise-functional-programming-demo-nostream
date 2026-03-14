package com.gloriane;

// Stream: filter people by age rule and print matching names
import java.util.Arrays;
import java.util.List;

public class ExampleWithPersonRule {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
            new Person("Alice", 25, "NYC", true),
            new Person("Bob", 35, "LA", false),
            new Person("Charlie", 30, "NYC", true)
        );

        PersonRule ageRule = person -> person.getAge() > 28;
        List<Person> result = PersonProcessor.findPeople(people, ageRule);
        
        PersonAction printAction = person -> System.out.println(person.getName());
        PersonProcessor.applyToMatching(people, ageRule, printAction);
    }
}
