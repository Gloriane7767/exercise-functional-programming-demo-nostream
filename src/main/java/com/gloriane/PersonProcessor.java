package com.gloriane;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PersonProcessor { //replace PersonRule with Predicate<Person>
    public static List<Person> findPeople(List<Person> list, Predicate<Person> rule) {
        List<Person> result = new ArrayList<>();
        for (Person person : list) {
            if (rule.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
// same here
    public static void applyToMatching(List<Person> list, Predicate<Person> rule, PersonAction action) {
        for (Person person : list) {
            if (rule.test(person)) {
                action.perform(person);
            }
        }
    }

    /*
 4. PersonProcessor Class - The Worker
This class does the actual work:
findPeople(): Takes a list of people and a rule, returns only people who pass the test
applyToMatching(): Takes a list, finds people who match a rule, then performs an action on them
     */
}

