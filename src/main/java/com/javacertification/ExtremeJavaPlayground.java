package com.javacertification;

import java.io.Console;
import java.io.RandomAccessFile;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

class ExtremeJavaPlayground {

    public static void main(String[] args) throws Throwable {
//        ProgressMonitorInputStream
//        ReflectionFactory.
        MethodHandle compareTo = MethodHandles.lookup().findVirtual(
            String.class, "compareTo",
            MethodType.methodType(int.class, String.class));
        ThreadLocalRandom.current().ints();
        int a = 10;
        int b = 20;
        a += (a = 4);
        b = b  + (b = 5);
        System.out.println(a); // 14
        System.out.println(b); // 25
        
    }

    public void t() {
        this.s = "";
    }

    static String s;

    interface enc {
        public default String toto() {
            return "toto";
        }
    }

    enum toto {
        D("asd");
        static String S = "";
        String name;

        toto(String asd) {
        }
    }
}
// Use @Serial on members that impact serialization
// ObjectOutputStream and InputStream rely on caches internally
// null vs EOFException
// debug arraylist writeObject readObject
// writeUTF does not take strings longer than 32 bits (i.e 65000)
// heap c -> ram, heap Java ram logic
// all objects reside in the heap, which itself resides in C's heap. It's organized by the JVM
// - one thread per stack space, contains the method stack, local variables and parameters
// all method calls reside in the stack
// Check what escape analysis actually is
// https://www.youtube.com/watch?v=_D6YTgavdNI
// check WeakReference
// -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/path
// volatile is recommended for multi threading for read only
// types of iterators: Fail-fast, weakly-consistent and snapshot
// example cleaner, FileChannel
// check if linkedHashMap orders based on the access
// Check ThreadLocalRandom.current()
// Check -enablesystemassertions
// Document [d][dd].M.[yyyy][yy]

record Jour(int x, String y) {
    public Jour() {
        this(1, "");
//        this.x = 1;
//        this.y = "";
    }

    Jour(int x, String y) {
        this.x = x;
        this.y = y;
    }
}
