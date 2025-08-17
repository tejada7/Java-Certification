package com.javacertification.design_patterns.behavioral.memento;

import java.io.*;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.Optional.ofNullable;

public class TestMap implements Serializable {

    static class A extends Thread {
        
        @Override
        public void run() {
            try {
                System.out.println("started");
                Thread.sleep(3_000);
                System.out.println("finished");
            } catch (InterruptedException e) {
                System.out.println("interrupted");
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws ParseException, IOException, ClassNotFoundException, InterruptedException {
        int a = 4;
        int b = ~a;
        int c = a^b;
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

    record Book(String isbn, String title) {

    }

    static class Books extends ArrayList<Book> {
        public Books() {
        }
    }

    public static double compute(double base, Function<Integer, Integer> f) {
        return (double) f.apply((int) base);
    }

    public static void read(Reader1 reader) {
//        new TreeMap<Object, Object>()
        ;
    }

    interface Reader1 {
        abstract void read(Book book);
    }

    static abstract class Reader {
        abstract void read(Book book);
    }

//    record Book(String isbn, String title, String author, String genre) {}

    static class X extends Mere implements inter{
//        var x = new ArrayList<Object>();
    }

    static abstract class Mere {
        public static void toto() {

        }
    }

    interface inter {

        static void toto() {

        }
    }

    static Integer kringler(Integer x) {
        Integer y = x + 10;
        x++;
        System.out.println(x);
        return y;
    }

    record TestClass(ReadWriteLock readWriteLock, Map<String, Object> map) {
        TestClass() {
            this(new ReentrantReadWriteLock(), new HashMap<>());
        }

        @Override
        public Map<String, Object> map() {
            try {
                readWriteLock.readLock().lock();
                return map;
            } finally {
                readWriteLock.readLock().unlock();
            }
        }

        public void updateMap(Map.Entry<String, Object> entry) {
            try {
                readWriteLock.writeLock().lock();
                map.put(entry.getKey(), entry.getValue());
            } finally {
                readWriteLock.writeLock().unlock();
            }
        }
    }

    void crazyLoop() {
        var c = 0;
        JACK:
        while (c < 8) {
            System.out.println(c);
            JILL:
            if (c > 3) break JILL;
            else c++;
        }
    }

    class Tata extends Toto {
        @Override
        int aMethod() {
            return super.aMethod();
        }
    }

    class Toto implements I1, I2 {

        int aMethod() {
            return  0;
        }
        @Override
        public void foo() throws FileNotFoundException {
            String s = "tre";
            final var stringBuilder = new StringBuilder();
        }

        public List<? extends String> list() {
            return null;
        }
    }

    interface I1 {
        void foo() throws IOException;
        
        List<? extends CharSequence> list();
    }

    interface I2 {
        void foo() throws FileNotFoundException;
    }
}
