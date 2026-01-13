package com.gloriane;

@FunctionalInterface
public interface PersonRule {
    boolean apply(Person person);
}
/*
Defines a contract for testing/filtering Person objects.
Connection to Main: Main creates PersonRule lambdas to filter people by different criteria.
 */