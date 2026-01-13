package com.gloriane;

import java.util.ArrayList;
import java.util.List;

public class PersonProcessor {
    public static List<Person> findPeople(List<Person> list, PersonRule rule) {
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

