package com.gloriane;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {

        List<Person> people = List.of(
                new Person("Amina", 22, "Stockholm", true),
                new Person("Erik", 17, "Uppsala", true),
                new Person("Noah", 34, "Stockholm", false),
                new Person("Sara", 29, "Gothenburg", true),
                new Person("Lina", 41, "Malmö", false),
                new Person("Omar", 19, "Stockholm", true)
        );
/*
    // Using custom functional interfaces for filtering and actions
        PersonRule isActive = p -> p.isActive();

        PersonRule livesInStockholm = p -> p.getCity().equals("Stockholm");

        PersonRule isAdult = p -> p.getAge() >= 18;
        List<Person> adults = filterPeople(people, isAdult);
        PersonAction printName = p -> System.out.println(p.getName());
        for (Person p : adults) {
            printName.execute(p);
        }

        sytem.out.println("Active people: "+ filterPeople(people, isActive));
        system.out.println();

        system.out.println("People in Stockholm: "+ filterPeople(people, livesInStock
        system.out.println();

        system.out.println("Adults: " + adults);
        system.out.println();

*/
        // Define Predicates and Consumer
        Predicate<Person> isActive = Person::isActive;
        Predicate<Person> isAdult = p -> p.getAge() >= 18;
        Predicate<Person> livesInStockholm = p -> p.getCity().equals("Stockholm");
        Consumer<Person> sendEmailAction = p -> System.out.println("Sending email to: " + p.getName());

        // Using Predicates in streams
        List<Person>activePeople =
                people.stream()
                        .filter(isActive)
                        .toList();

        long adultCount =
                people.stream()
                        .filter(isAdult)
                        .count();
        List<Person> sortedByAge =
                people.stream()
                        .sorted(Comparator.comparingInt(Person::getAge))
                        .toList();

        Optional<Person> firstActiveInStockholm =
                people.stream()
                        .filter(isActive)
                        .filter(livesInStockholm)
                        .findFirst();

        // Predicate composition: and, or, negate
        List<Person> activeAdults =
                people.stream()
                        .filter(isActive.and(isAdult))
                        .toList();

        List<Person> adultsOrInStockholm =
                people.stream()
                        .filter(isAdult.or(livesInStockholm))
                        .toList();

        List<Person> inActivePeople =
                people.stream()
                        .filter(isActive.negate())
                        .filter(isAdult)
                        .toList();

        List<String> cities =
                people.stream()
                        .map(Person::getCity)
                        .distinct()
                        .sorted()
                        .toList();

        List<String> firstLetters =
                people.stream()
                        .map(p -> p.getName().substring(0, 1))
                        .distinct()
                        .sorted()
                        .toList();

        List<String> formatted =
                people.stream()
                        .map(p -> p.getName() + " (" + p.getAge() + ") - " + p.getCity())
                        .toList();

        List<String> emailList =
                people.stream()
                        .map(p -> p.getName().toLowerCase() + "@example.com")
                        .toList();

        System.out.println("=== PERSON ANALYSIS ===");
        System.out.println();

        System.out.println("Active people: " + activePeople);
        System.out.println();

        System.out.println("Adult count: " + adultCount);
        System.out.println();

        System.out.println("Sorted by age: " + sortedByAge);
        System.out.println();

        System.out.println("First active in Stockholm: " + firstActiveInStockholm);
        System.out.println();

        System.out.println("Active adults: " + activeAdults);
        System.out.println();

        System.out.println("Adults or in Stockholm: " + adultsOrInStockholm);
        System.out.println();

        System.out.println("Inactive people: " + inActivePeople);
        System.out.println();

        System.out.println("Cities: " + cities);
        System.out.println();

        System.out.println("First letters: " + firstLetters);
        System.out.println();

        System.out.println("Formatted: " + formatted);
        System.out.println();

        System.out.println("Email list: " + emailList);
        System.out.println();

        System.out.println("\nUsing Consumer:");
        activePeople.forEach(sendEmailAction);
    }

    public static List<Person> filterPeople(List<Person> people, PersonRule rule) {
        List<Person> result = new ArrayList<>();
        for (Person person : people) {
            if (rule.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}
