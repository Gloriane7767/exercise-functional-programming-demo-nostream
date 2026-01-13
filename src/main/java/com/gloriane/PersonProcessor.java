package com.gloriane;

import java.util.ArrayList;
import java.util.List;

public class PersonProcessor {
    public static List<Person> findPeople(List<Person> list, PersonRule rule)
    public static void applyToMatching(List<Person> list, PersonRule rule, PersonAction action) {

        List<Person> result = new ArrayList<>();
        for (Person person : list) {
            if (rule.apply(person)) {
                result.add(person);
            }
        }
        return result;
    }

    public static void applyToMatching(List<Person> list, PersonRule rule, PersonAction action) {
        for (Person person : list) {
            if (rule.apply(person)) {
                if (action.getPrintName() != null) {
                    System.out.println(action.getPrintName() + ": " + person.getName());
                }
                if (action.getSendEmail() != null) {
                    // Simulate sending an email
                    System.out.println("Sending email to: " + person.getName());
                }
            }

        }
    }
}
/*
Processes Person lists using rules and actions.
Connection to Main: Main calls these static methods to filter and process people.
 */