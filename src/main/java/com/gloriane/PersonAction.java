package com.gloriane;

@FunctionalInterface
public interface PersonAction {
    void execute(Person person);
}
/*
3. PersonAction Interface - The Do Something
This defines what action you want to perform on a person. It's like saying "do this thing to this person."
Examples of actions:
"Send an email to this person"
"Send adult newsletter to this person"
"Print a message about this person"
 */