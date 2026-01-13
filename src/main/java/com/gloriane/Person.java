package com.gloriane;


public class Person {
    private String name;
    private int age;
    private String city;
    private boolean active;

    public Person(String name, int age, String city, boolean active) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                ", active=" + active +
                '}';
    }
}
/*
This is a functional programming demonstration that shows how to filter and process a list of people using different criteria. Think of it like a simple database of people where you can ask questions like "show me all active people" or "find all adults from Stockholm."
1. Person Class - The Data Container
This is like a digital business card that holds information about a person:
Name (like "Amina")
Age (like 22)
City (like "Stockholm")
Active status (true/false - maybe if they're a current member of something)
It's just a simple data holder with getters/setters and a way to print the person's info nicely.
 */