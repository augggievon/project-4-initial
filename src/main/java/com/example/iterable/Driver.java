package com.example.iterable;

public class Driver {

    public static void main(String[] args) {
        Bag<String> bag = new Bag<>();
        bag.add("apple");
        bag.add("banana");
        bag.add("orange");
        bag.add("pear");
        bag.add("apple");


        System.out.println("Bag contains 'banana': " + bag.contains("banana"));
        System.out.println("Bag size: " + bag.size());

        System.out.println("Items in the bag:");
        for (String item : bag) {
            System.out.println(item);
        }

        bag.remove("banana");
        System.out.println("Bag contains 'banana' after removal: " + bag.contains("banana"));

        System.out.println("Bag size: " + bag.size());
        for (String item : bag) {
            System.out.println(item);
        }

        bag.spliterator().forEachRemaining(item -> System.out.println("Spliterator item: " + item));
        System.out.println("Bag contains 'pear' after removal: " + bag.contains("pear"));

        bag.forEach(System.out::println);
    }
}
