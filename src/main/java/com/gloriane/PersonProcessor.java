package com.gloriane;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Consumer;

public class PersonProcessor { //replace PersonRule with Predicate<Person>
    public static List<Person> findPeople(
            List<Person> people,
    PersonRule rule
    ) {
        List<Person> result = new ArrayList<>();
        for (Person person : people) {
            if (rule.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
// same here
    public static void applyToMatching(
            List<Person> people,
            PersonRule rule,
            PersonAction action
    ) {
        for (Person person : people) {
            if (rule.test(person)) {
                action.execute(person);
            }
        }
    }

    /*
 4. PersonProcessor Class - The Worker
This method:
Loops through people
Applies the rule
Keeps only matching ones
Look at each person
Ask the rule a question
If answer = true, keep them
     */
}

