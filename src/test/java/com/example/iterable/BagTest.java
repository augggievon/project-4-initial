package com.example.iterable;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class BagTest {

    @Test
    void testAddContainsSize() {
        Bag<String> bag = new Bag<>();

        bag.add("apple");
        bag.add("banana");

        assertTrue(bag.contains("apple"));
        assertTrue(bag.contains("banana"));
        assertEquals(2, bag.size());
        assertFalse(bag.isEmpty());
    }

    @Test
    void testRemove() {
        Bag<String> bag = new Bag<>();

        bag.add("apple");
        bag.add("banana");

        assertTrue(bag.remove("banana"));
        assertFalse(bag.contains("banana"));
        assertEquals(1, bag.size());

        // removing non-existent item
        assertFalse(bag.remove("orange"));
    }

    @Test
    void testDuplicateRemoval() {
        Bag<String> bag = new Bag<>();

        bag.add("apple");
        bag.add("apple");

        assertEquals(2, bag.size());

        bag.remove("apple"); // should remove only one

        assertEquals(1, bag.size());
        assertTrue(bag.contains("apple"));
    }

    @Test
    void testIterator() {
        Bag<String> bag = new Bag<>();

        bag.add("A");
        bag.add("B");
        bag.add("C");

        Iterator<String> it = bag.iterator();

        List<String> result = new ArrayList<>();
        while (it.hasNext()) {
            result.add(it.next());
        }

        assertEquals(List.of("A", "B", "C"), result);
    }

    @Test
    void testForEach() {
        Bag<String> bag = new Bag<>();

        bag.add("A");
        bag.add("B");
        bag.add("C");

        List<String> result = new ArrayList<>();

        bag.forEach(result::add);

        assertEquals(List.of("A", "B", "C"), result);
    }

    @Test
    void testSpliterator() {
        Bag<String> bag = new Bag<>();

        bag.add("A");
        bag.add("B");
        bag.add("C");

        List<String> result = new ArrayList<>();

        bag.spliterator().forEachRemaining(result::add);

        assertEquals(List.of("A", "B", "C"), result);
    }

    @Test
    void testAllIterationMethodsMatch() {
        Bag<String> bag = new Bag<>();

        bag.add("X");
        bag.add("Y");
        bag.add("Z");

        List<String> iteratorList = new ArrayList<>();
        for (String item : bag) {
            iteratorList.add(item);
        }

        List<String> forEachList = new ArrayList<>();
        bag.forEach(forEachList::add);

        List<String> spliteratorList = new ArrayList<>();
        bag.spliterator().forEachRemaining(spliteratorList::add);

        assertEquals(iteratorList, forEachList);
        assertEquals(iteratorList, spliteratorList);
    }

    @Test
    void testEmptyBag() {
        Bag<String> bag = new Bag<>();

        assertTrue(bag.isEmpty());
        assertEquals(0, bag.size());
        assertFalse(bag.contains("anything"));
        assertFalse(bag.remove("anything"));

        // iteration should do nothing
        AtomicInteger count = new AtomicInteger(0);
        bag.forEach(e -> count.incrementAndGet());

        assertEquals(0, count.get());
    }

    @Test
    void testNullHandling() {
        Bag<String> bag = new Bag<>();

        bag.add(null);

        assertTrue(bag.contains(null));
        assertEquals(1, bag.size());

        assertTrue(bag.remove(null));
        assertFalse(bag.contains(null));
    }
}