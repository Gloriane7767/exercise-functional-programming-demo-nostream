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

        // ── SECTION 1: Custom functional interfaces (PersonRule & PersonAction) ──

        PersonRule isActiveRule = p -> p.isActive();           // lambda: true if person.isActive() e.g. Amina -> true
        PersonRule isAdultRule = p -> p.getAge() >= 18;        // lambda: true if age >= 18, e.g. Erik(17) -> false, Amina(22) -> true
        PersonRule livesInStockholmRule = p -> p.getCity().equals("Stockholm"); // lambda: true if city is Stockholm, e.g. Noah -> true
        List<Person> adults = filterPeople(people, isAdultRule);

        PersonAction printName = p -> System.out.println(p.getName());                    // action: prints name, e.g. "Amina"
        PersonAction sendEmail = p -> System.out.println("Send email to " + p.getName()); // action: prints "Send email to Amina"

        System.out.println("=== CUSTOM INTERFACE SECTION ===");
        System.out.println();

        System.out.println("Active people: " + filterPeople(people, isActiveRule));
        System.out.println();

        System.out.println("Adults: " + adults);
        System.out.println();

        System.out.println("People in Stockholm: " + filterPeople(people, livesInStockholmRule));
        System.out.println();

        System.out.println("Adult names (via PersonAction):");
        for (Person p : adults) {
            printName.execute(p);
        }
        System.out.println();

        System.out.println("Send email actions (via PersonAction):");
        for (Person p : adults) {
            sendEmail.execute(p);
        }
        System.out.println();

        // ── SECTION 2: Built-in Predicate & Consumer ──

        Predicate<Person> isActive = p -> p.isActive();                          // lambda: e.g. Amina -> true, Noah -> false
        Predicate<Person> isAdult = p -> p.getAge() >= 18;                      // lambda: e.g. Erik(17) -> false, Sara(29) -> true
        Predicate<Person> livesInStockholm = p -> p.getCity().equals("Stockholm"); // lambda: e.g. Amina -> true, Sara -> false
        Consumer<Person> printNameAction = p -> System.out.println(p.getName());                    // consumer: prints name, e.g. "Amina"
        Consumer<Person> sendEmailAction = p -> System.out.println("Send email to " + p.getName()); // consumer: prints "Send email to Amina"

        List<Person> activePeople =
                people.stream()
                        .filter(isActive)   // keeps only active people: [Amina, Erik, Sara, Omar]
                        .toList();

        long adultCount =
                people.stream()
                        .filter(isAdult)    // counts people aged >= 18: Amina, Noah, Sara, Lina, Omar = 5
                        .count();

        List<Person> sortedByAge =
                people.stream()
                        .sorted(Comparator.comparingInt(p -> p.getAge())) // ascending by age: Erik(17), Omar(19), Amina(22)...
                        .toList();

        Optional<Person> firstActiveInStockholm =
                people.stream()
                        .filter(isActive)        // active: Amina, Erik, Sara, Omar
                        .filter(livesInStockholm) // in Stockholm: Amina, Omar -> first is Amina
                        .findFirst();

        // Predicate composition: and, or, negate
        List<Person> activeAdults =
                people.stream()
                        .filter(isActive.and(isAdult)) // both active AND adult: e.g. Amina(22,active), not Erik(17,active)
                        .toList();

        List<Person> adultsOrInStockholm =
                people.stream()
                        .filter(isAdult.or(livesInStockholm)) // adult OR in Stockholm: e.g. Noah(34,Stockholm) included even if inactive
                        .toList();

        List<Person> inActivePeople =
                people.stream()
                        .filter(isActive.negate()) // NOT active: Noah, Lina
                        .filter(isAdult)            // AND adult: Noah(34), Lina(41)
                        .toList();

        List<String> cities =
                people.stream()
                        .map(p -> p.getCity())  // extracts city from each person
                        .distinct()            // removes duplicates: Stockholm appears 3 times -> once
                        .sorted()              // alphabetical: [Gothenburg, Malmö, Stockholm, Uppsala]
                        .toList();

        List<String> firstLetters =
                people.stream()
                        .map(p -> p.getName().substring(0, 1)) // first letter: A, E, N, S, L, O
                        .distinct()                             // removes duplicates
                        .sorted()                               // alphabetical order
                        .toList();

        List<String> formatted =
                people.stream()
                        .map(p -> p.getName() + " (" + p.getAge() + ") - " + p.getCity()) // e.g. "Amina (22) - Stockholm"
                        .toList();

        List<String> emailList =
                people.stream()
                        .map(p -> p.getName().toLowerCase() + "@example.com") // e.g. "amina@example.com"
                        .toList();

        System.out.println("=== PREDICATE & CONSUMER SECTION ===");
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

        System.out.println("Using Consumer (print name):");
        activePeople.forEach(printNameAction);
        System.out.println();

        System.out.println("Using Consumer (send email):");
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
