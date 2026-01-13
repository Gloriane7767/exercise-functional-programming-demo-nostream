package com.gloriane;

@FunctionalInterface
public interface PersonRule {
    boolean apply(Person person);
}
/*
This is like asking a yes/no question about a person. It's a contract that says "I can test any person and give you true or false."
Examples of rules:
"Is this person active?"
"Is this person an adult (18+)?"
"Does this person live in Stockholm?"
 */