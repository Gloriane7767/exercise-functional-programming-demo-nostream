package com.gloriane;

@FunctionalInterface
public interface PersonRule {
    boolean test(Person person);
}
