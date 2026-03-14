package com.gloriane;

import java.util.Arrays;
import java.util.List;

public class ExampleWithPredicate {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
            new Person("Alice", 25, "NYC", true),
            new Person("Bob", 35, "LA", false),
            new Person("Charlie", 30, "NYC", true)
        );

        List<Person> result = PersonProcessorRefactored.findPeople(
            people, 
            person -> person.getAge() > 28
        );
        
        PersonProcessorRefactored.applyToMatching(
            people, 
            person -> person.getAge() > 28,
            person -> System.out.println(person.getName())
        );
    }
}
