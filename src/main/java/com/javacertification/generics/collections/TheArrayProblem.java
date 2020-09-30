package com.javacertification.generics.collections;

import java.util.*;

public class TheArrayProblem {

    public static void main(String[] args) {
        Person donDrapwe = new Person("Don Draper", 89);
        Person peggyOlson = new Person("Peggy Olson", 65);

        usingArrays(donDrapwe, peggyOlson);
        usingLists(donDrapwe, peggyOlson);
        usingSets(donDrapwe, peggyOlson);
        usingMaps(donDrapwe, peggyOlson);

    }

    private static void usingMaps(Person donDrapwe, Person peggyOlson) {
        Map<String, Person> madMen = new HashMap<>();
        madMen.put(donDrapwe.getName(), donDrapwe);
        madMen.put(peggyOlson.getName(), peggyOlson);

        System.out.println(madMen);

        for (String name : madMen.keySet()) {

        }

        for (Person person : madMen.values()) {

        }

        for (Map.Entry<String, Person> entry : madMen.entrySet()) {

        }
    }

    private static void usingSets(Person donDrapwe, Person peggyOlson) {
        Set<Person> madMen = new HashSet<>();
        madMen.add(donDrapwe);
        madMen.add(peggyOlson);
        madMen.add(donDrapwe);
        Person bertCooper = new Person("Bert Cooper", 100);

        System.out.println(madMen.contains(donDrapwe));
        System.out.println(madMen.contains(bertCooper));

        for (Person person : madMen) {
            System.out.println(person);
        }
    }

    private static void usingLists(Person donDrapwe, Person peggyOlson) {
        List<Person> madMen = new ArrayList<>();
        madMen.add(donDrapwe);
        madMen.add(peggyOlson);

        System.out.println(madMen);
        System.out.println("El tamaño del vector es: " + madMen.size());

        Person bertCooper = new Person("Bert Cooper", 100);
        madMen.add(bertCooper);

        System.out.println(madMen);
        System.out.println("El tamaño del vector es: " + madMen.size());
    }

    private static void usingArrays(Person donDrapwe, Person peggyOlson) {
        Person[] madMen = {donDrapwe, peggyOlson};

        System.out.println(Arrays.toString(madMen));
        System.out.println("El tamaño del vector es: " + madMen.length);

        Person bertCooper = new Person("Bert Cooper", 100);
        madMen = add(bertCooper, madMen);

        System.out.println(Arrays.toString(madMen));

        System.out.println("El tamaño del vector es: " + madMen.length);
    }

    private static Person[] add(Person person, Person[] array) {
        final int length = array.length;
        array = Arrays.copyOf(array, length + 1);
        array[length] = person;
        return array;
    }

    static class Clase<T> {
        T methodo(){return null;}
    }
}
