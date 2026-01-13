package com.gloriane;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static java.util.stream.StreamSupport.stream;

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
    /*
        // Rules using lambda expressions for filtering
        PersonRule isActive = person -> person.isActive();
        PersonRule isAdult = person -> person.getAge() >= 18;
        PersonRule livesInStockholm = person -> person.getCity().equals("Stockholm");
*/
        // Using predefined method for filtering
        Predicate<Person> isActive = person -> person.isActive();
        Predicate<Person> isAdult = person -> person.getAge() >= 18;
        Predicate<Person> livesInStockholm = person -> person.getCity().equals("Stockholm");

    /*
        // Rules using lambda expressions for actions
       PersonAction emailAction = person -> {
            System.out.println("Sending email to: " + person.getName());
        };

        PersonAction combinedAction = person -> {
            System.out.println("Printing name to: " + person.getName());
            System.out.println("Sending email to: " + person.getName());
        };
    */
        // Using predefined method for actions
        PersonAction emailAction = person -> {
            System.out.println("Sending email to: " + person.getName());
        };

        PersonAction combinedAction = person -> {
            System.out.println("Printing name to: " + person.getName());
            System.out.println("Sending email to: " + person.getName());
        };
    // Rules using lambda expressions for processing
        System.out.println(".................Active people:..............");
        List<Person> activePeople = PersonProcessor.findPeople(people, (Predicate<Person>) isActive);
        activePeople.forEach(System.out::println);

        System.out.println("..................Adults:.................");
        List<Person> adultPeople = PersonProcessor.findPeople(people, (Predicate<Person>) isAdult);
        adultPeople.forEach(System.out::println);

        System.out.println("......Adults from Stockholm:.....");
        List<Person> stockholmAdults = PersonProcessor.findPeople(people, (person) -> isAdult.test(person) && livesInStockholm.test(person));
        stockholmAdults.forEach(System.out::println);

        System.out.println("......Applying combined action to active people:.....");
        PersonProcessor.applyToMatching(people, (Predicate<Person>) isActive, combinedAction);
    }






    // Imperative Style
    public static List<Person> filterPeople(List<Person> people, PersonRule filter) {
        List<Person> filteredPeople = new ArrayList<>();
        for (Person person : people) {
            if (filter.apply(person)) {
                filteredPeople.add(person);
            }
        }
        return filteredPeople;
    }
}
