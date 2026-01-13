package com.gloriane;

@FunctionalInterface
public interface PersonRule {
    boolean apply(Person person);
}
