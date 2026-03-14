package com.gloriane;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class OptionalTasksDemo {
    public static void main(String[] args) {

        List<Person> people = List.of(
                new Person("Amina", 22, "Stockholm", true),
                new Person("Erik",  17, "Uppsala",   true),
                new Person("Noah",  34, "Stockholm", false),
                new Person("Sara",  29, "Gothenburg",true),
                new Person("Lina",  41, "Malmö",     false),
                new Person("Omar",  19, "Stockholm", true)
        );

        // ── TASK 5: Rule Composition ──────────────────────────────────────────

        // 5a) Using custom PersonRule (no built-in composition — combine manually)
        PersonRule isActive          = p -> p.isActive();
        PersonRule isAdult           = p -> p.getAge() >= 18;
        PersonRule livesInStockholm  = p -> p.getCity().equals("Stockholm");

        PersonRule activeAndAdult        = p -> isActive.test(p) && isAdult.test(p);
        PersonRule adultOrInStockholm    = p -> isAdult.test(p)  || livesInStockholm.test(p);
        PersonRule notActive             = p -> !isActive.test(p);

        System.out.println("=== TASK 5: Rule Composition (PersonRule) ===");
        System.out.println("Active AND adult:        " + PersonProcessor.findPeople(people, activeAndAdult));
        System.out.println("Adult OR in Stockholm:   " + PersonProcessor.findPeople(people, adultOrInStockholm));
        System.out.println("NOT active:              " + PersonProcessor.findPeople(people, notActive));
        System.out.println();

        // 5b) Same rules using Predicate<Person> — built-in .and() / .or() / .negate()
        Predicate<Person> pIsActive         = p -> p.isActive();
        Predicate<Person> pIsAdult          = p -> p.getAge() >= 18;
        Predicate<Person> pLivesInStockholm = p -> p.getCity().equals("Stockholm");

        Predicate<Person> pActiveAndAdult     = pIsActive.and(pIsAdult);
        Predicate<Person> pAdultOrStockholm   = pIsAdult.or(pLivesInStockholm);
        Predicate<Person> pNotActive          = pIsActive.negate();

        System.out.println("=== TASK 5: Rule Composition (Predicate) ===");
        System.out.println("Active AND adult:        " + PersonProcessorRefactored.findPeople(people, pActiveAndAdult));
        System.out.println("Adult OR in Stockholm:   " + PersonProcessorRefactored.findPeople(people, pAdultOrStockholm));
        System.out.println("NOT active:              " + PersonProcessorRefactored.findPeople(people, pNotActive));
        System.out.println();

        // ── TASK 6: Reusable Processor (PersonProcessor with PersonRule) ──────

        System.out.println("=== TASK 6: PersonProcessor (PersonRule + PersonAction) ===");

        List<Person> activeAdults = PersonProcessor.findPeople(people, activeAndAdult);
        System.out.println("findPeople (active adults): " + activeAdults);

        PersonAction printName = p -> System.out.println("  → " + p.getName());
        System.out.println("applyToMatching (print active adults):");
        PersonProcessor.applyToMatching(people, activeAndAdult, printName);
        System.out.println();

        // ── TASK 7: Prebuilt Interfaces (Predicate + Consumer) ────────────────

        System.out.println("=== TASK 7: PersonProcessorRefactored (Predicate + Consumer) ===");

        List<Person> result = PersonProcessorRefactored.findPeople(people, pActiveAndAdult);
        System.out.println("findPeople (active adults): " + result);

        Consumer<Person> sendEmail = p -> System.out.println("  → Send email to " + p.getName());
        System.out.println("applyToMatching (email active adults):");
        PersonProcessorRefactored.applyToMatching(people, pActiveAndAdult, sendEmail);
    }
}
