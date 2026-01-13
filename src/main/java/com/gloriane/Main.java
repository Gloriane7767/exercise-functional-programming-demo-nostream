package com.gloriane;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> people = List.of(
                new Person("Amina", 22, "Stockholm", true),
                new Person("Erik", 17, "Uppsala", true),
                new Person("Noah", 34, "Stockholm", false),
                new Person("Sara", 29, "Gothenburg", true),
                new Person("Lina", 41, "Malmö", false),
                new Person("Omar", 19, "Stockholm", true));

        for(Person person : people) {
            System.out.println(person);
        }

    // Rules
        PersonRule active = (Person person) -> person.isActive();
        PersonRule adults = (Person person) -> person.getAge() >= 18;
        PersonRule fromStockholm = (Person person) -> person.getCity().equals("Stockholm");

        PersonRule isActive = person -> person.isActive();
        PersonRule isAdult = person -> person.getAge() >= 18;
        PersonRule livesInStockholm = person -> person.getCity().equals("Stockholm");

        // Using findPeople - returns a list
        List<Person> activePeople = PersonProcessor.findPeople (people, isActive);
        List<Person> adultPeople = PersonProcessor.findPeople (people, isAdult);

        // Using applyToMatching - performs actions
        PersonAction activeAction = new PersonAction("Active", null);
        PersonProcessor.applyToMatching(people, isActive, activeAction);

        PersonAction adultAction = new PersonAction("Adult", null);
        PersonProcessor.applyToMatching(people, isAdult, adultAction);

        // Combine rules using lambda expressions
        PersonRule ActiveAndAdult = (Person person) -> active.apply(person) && adults.apply(person);
        PersonRule AdultOrStockholm = (Person person) -> adults.apply(person) || livesInStockholm.apply(person);
        PersonRule notActive = (Person person) -> !isActive.apply(person);

        PersonRule activeAndAdult = person -> isActive.apply(person) && isAdult.apply(person);
        PersonAction activeAdultAction = new PersonAction("Active Adult", null);
        PersonProcessor.applyToMatching(people, activeAndAdult, activeAdultAction);

        System.out.println("\nActive people:");
        List<Person> activePeople = filterPeople(people, active);
        activePeople.forEach(System.out::println);

        System.out.println("\nAdults:");
        List<Person> adultPeople = filterPeople(people, adults);
        adultPeople.forEach(System.out::println);

        System.out.println("\nAdults from Stockholm:");
        List<Person> stockholmAdults = filterPeople(people, (person) -> adults.apply(person) && fromStockholm.apply(person));
        stockholmAdults.forEach(System.out::println);
    }

    // Imperative Style
    public static List<Person> filterPeople(List<Person> people, PersonRule rule) {
        List<Person> filteredPeople = new ArrayList<>();
        for (Person person : people) {
            if (rule.apply(person)) {
                filteredPeople.add(person);
            }
        }
        return filteredPeople;
    }
