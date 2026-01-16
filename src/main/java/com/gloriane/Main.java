package com.gloriane;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    // Using custom functional interfaces for filtering and actions
        PersonRule isActive = p -> p.isActive();

        PersonRule livesInStockholm = p -> p.getCity().equals("Stockholm");

        PersonRule isAdult = p -> p.getAge() >= 18;
        List<Person> adults = filterPeople(people, isAdult);
        PersonAction printName = p -> System.out.println(p.getName());
        for (Person p : adults) {
            printName.execute(p);
        }

        // Using predefined functional interfaces for filtering and actions
        List<Person>activePeople =
                people.stream()
                        .filter(Person::isActive)
                        .toList();

        long adaultCount =
                people.stream()
                        .filter(p -> p.getAge() >= 18)
                        .count();
        List<Person> sortedByAge =
                people.stream()
                        .sorted(Comparator.comparingInt(Person::getAge))
                        .toList();

        Optional<Person> firstActiveInStockholm =
                people.stream()
                        .filter(Person::isActive)
                        .filter(p -> p.getCity().equals("Stockholm"))
                        .findFirst();

        // Multiple Filterring Methods

        List<Person> activeAdults =
                people.stream()
                        .filter(p -> p.isActive() && p.getAge() >= 18)
                        .toList();

        List<Person> adultsOrInStockholm =
                people.stream()
                        .filter(p -> p.getAge() >= 18 || p.getCity().equals("Stockholm"))
                        .toList();

        List<Person> inActivePeople =
                people.stream()
                        .filter(p -> !p.isActive())
                        .filter(p -> p.getAge() >= 18)
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
    /*
        // Peronal Rules for filtering using lambda expressions
        PersonRule isActive = person -> person.isActive();
        PersonRule isAdult = person -> person.getAge() >= 18;
        PersonRule livesInStockholm = person -> person.getCity().equals("Stockholm");

        // Personal Rules for actions using lambda expressions
        PersonAction printName = person -> System.out.println(person.getName());
        PersonAction sendEmail = person -> System.out.println("Send email to " + person.getName());};

        //CombinedRules active AND adult
        PersonRule activeAndAdult = p -> isActive.test(p) && isAdult.test(p);

        // adult OR lives in Stockholm
        PersonRule adultOrStockholm = p -> isAdult.test(p) || livesInStockholm.test(p);

        // NOT active
        PersonRule notActive = p -> !isActive.test(p);
     */

        // Using Java's predefined functional interfaces for filtering and actions
        Predicate<Person> isActive = person -> person.isActive();
        Predicate<Person> isAdult = person -> person.getAge() >= 18;
        Predicate<Person> livesInStockholm = person -> person.getCity().equals("Stockholm");

        Consumer<Person> sendEmailAction = person -> {
            System.out.println("Sending email to: " + person.getName());
        };



}
