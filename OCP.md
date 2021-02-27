# Java 11 Oracle Certified Professional study notes

### Inner classes
* Static nested class
    * Can be instantiated without an instance of the enclosing class
    * I cannot access instance variables or methods of the outer class directly, it needs an explicit reference to the outer class.
    * The enclosing class can refer to the static fields and methods of the inner class
```java
package parent;
public class Parent {
    private String name;
    public static final String CONSTANT = "";

    public static class InnerStaticClass {
        public static void staticMethod() {
            System.out.println(new Parent().name);            
            System.out.println(CONSTANT);            
        }

        public void instanceMethod() {
            System.out.println(new Parent().name);            
            System.out.println(CONSTANT);         
        }
    } 
}
```

```java
package foo;
import parent.Parent.InnerStaticClass;
    class Foo {
        Parent.InnerClass.staticMethod();
        new Parent.InnerClass.instanceMethod();
    }   
```
* Member inner class (aka inner class)
* Anonymous class
* Local class.- Can be defined within methods, constructors and initializers.


### Java Collections Framework types
 
|Type|Can contain duplicate elements|Elements always ordered?|Has keys and value?|Must add/remove in specific order?|
|---|---|---|---|---|
|List|Yes|Yes (by index)|No|No|
|Set|No|No|No|No|
|Map|No for keys, yes for values|No|Yes|No| 
|Queue|Yes|Yes in defined order|No|Yes| 

### Collection attributes
|Type|Java Collections Framework interface|Sorted?|Calls hashcode?|Calls compareTo?|Allows null values|
|---|---|---|---|---|---|
|ArrayList|List|No|No|No|Yes|
|HashMap|Map|No|Yes|No|Yes|
|HashSet|Set|No|Yes|No|Yes|
|LinkedList|List, Queue|No|No|No|Yes|
|TreeMap|Map|Yes|No|Yes|No|
|TreeSet|Set|Yes|No|Yes|No|

> *A natural ordering that uses compareTo() is said to be consistent with equals if, and only if, x.equals(y) is true whenever x.compareTo(y) equals 0.*
### Comparator vs Comparator interfaces
|Difference|Comparable|Comparator|
|---|---|---|
|Package name|java.lang|java.util|
|Interface must be implemented by comparing class?|Yes|No|
|Method name in interface|compareTo(T e1)|compare(T e1, T e2)|
|Number of parameters|1|2|
|Common to declare a lambda|No|Yes|

### Naming Conventions for Generics  

A type parameter can be named whatever you want.
The convention is to use single uppercase letters to make it obvious that they aren't real class names. The following are common letters to use:
* E for an element
* K for a map key
* V for a map value
* N for a number
* T for a generic data type
* S, U, V, and so forth for multiple generic types

### What cannot be accomplished with generics
> *Type erasure means removing generic information within a class by replacing it with Object and the necessary casting
>, it happens when compiling and creating the bytecode.*

That being said, *reifiable types* can do anything that Java allows. Non-reifiable types have some limitations, generics are non-reifiable
and its limitation are: 
 
* Calling constructors (i.e. new T())
* Creating arrays of that generic type (e.g. new T[])
* Calling instanceof
* Using primitives as generic type parameter
* Creating static variables as generic type parameter 

### Wildcards

|Type of bound|Syntax|Example|
|---|---|---|
|Unbounded wildcard|?|`List<?> whatever = new ArrayList<String>()`|
|Wildcard with an upper bound|? extends type|`List<? extends Exception> exceptions = new ArrayList<RuntimeException>()`|
|Wildcard with a lower bound|? super type|`List<? super RuntimeException> exceptions = new ArrayList<Exception>()`|

From where, for upper bounds and unbounded wildcards, the list becomes logically immutable and therefore cannot be modified, 
 although it is technically possible to remove elements.

```java
List<?> list = new ArrayList() != var list = new ArrayList()
```
### Functional Interfaces reference table 
|Functional interface|Return type|Method name|# of parameters|
|---|---|---|---|
|Supplier<T>|T|get()|0|
|Consumer<T>|void|accept(T)|1(T)|
|BiConsumer<T,U>|void|accept(T, U)|2(T,U)|
|Predicate<T>|boolean|test(t)|1(T)|
|BiPredicate<T, U>|boolean|test(T, U)|2(T,U)|
|Function(T,R)|R|apply(T)|1(T)|
|BiFunction<T,U,R>|R|apply(T,U)|2(T,U)|
|UnaryOperator<T>|T|apply(T)|1(T)|
|BinaryOperator<T>|T|apply(T,T)|2(T,T)|
|Callable<T>|T|call()|0|
|Runnable<T>|void|run()|0|

### Streams
> With streams, the data isn't generated up front. It is created when needed. This is an example of lazy evaluation, which delays execution until necessary.
#### Stream pipeline's parts
* Source
* Intermediate operations
* Terminal operation

#### Infinite Streams
* `Stream.generate(Supplier(T))` -> Creates Stream by calling the Supplier for each element upon request
* `Stream.iterate(T seed, Predicate<T> hasNextCondition, UnaryOperator(T))` -> Creates Stream by using the seed for the first element and then calling the UnaryOperator for each subsequent element upon request. Stops if the Predicate returns false

#### Reduce method
* `Optional<T> reduce(BinaryOperator<T> accumulator)` -> accumulator is responsible for merging all Stream's elements T and return an optional of T. Example
    ```java
      List.of("h", "o", "l", "a").stream().reduce(String::concat); // Optional<"Hola">
  
      Arrays.stream(new String[]{})..reduce(String::concat); // Optional.empty
    ```
* `T reduce(T identity, BinaryOperator<T> accumulator)` -> identity is the starting value so that the accumulator sequentially merges other T Stream elements and return a T
    ```java
        List.of("h", "o", "l", "a").stream().reduce("¡" ,String::concat); // "¡Hola"
  
        Arrays.stream(new String[]{})..reduce("¡", String::concat); // "¡"
    ```
* `U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner)` -> identity is the starting value so that the accumulator sequentially transforms T to U, the combiner is only used for parallel streaming, and its purpose is to combine preliminary results.  
    ```java
        List.of("h", "o", "l", "a").stream().reduce(0, (identity, streamElement) -> identity + streamElement.length(), Integer::sum); // the combiner does not do anything here and the result is 4
 
        List.of("h", "o", "l", "a").stream().parallel().reduce(0, (identity, streamElement) -> identity + streamElement.length(), Integer::sum); // the result is 4 and the performance is better
    ```

#### Collect method
* `<R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner)` -> supplier is the function that creates the result container so that the accumulator can fold elements to the result container; finally, the combiner combines the partial result containers in the event it is a parallel stream.  
    ```java
        List.of("h", "o", "l", "a").stream().collect(StringBuilder::new, StringBuilder::append, StringBuilder::append); // the result will be a string builder with the greeting hola, it's worth noting that combines does nothing, unlike the belwo example: 
 
        List.of("h", "o", "l", "a").stream().parallel().collect(TreeSet::new, Set::add, Set::addAll); // Set<h,o,l,a>
    ```
### IO
In Java, although the file separator / can be used in both Unix-based and Microsoft Windows operating systems, it is recommended to use either:
```java
File.separator;
// Or
System.getProperty("file.separator");
```
That being said, there are two ways to instantiate files/directories in Java:
#### 1. File
Represents the path to a particular file or directory on the file system. The file class cannot read or write data **within** a file.
```java
new File (String pathname);
new File (File parent, String child);
new File (String parent, String child);
```
When working with an instance of the File class, keep in mind that it only represents a path to a file. Unless operated upon, it is not connected to an actual file within the file system.

Below an exhaustive list of its available methods:

|Method Name|Description|
|---|---|
|`boolean delete()`|Deletes the file or directory and returns true only if successful. If this instance denotes a directory, then the directory must be empty in order to be deleted.|
|`boolean exists()`|Checks if a file exists.|
|`String getAbsolutePath()`|Retrieves the absolute name of the file or directory within the file system.|
|`String getName()`|Retrieves the name of the file or directory.|
|`String getParent()`|Retrieves the parent directory that the path is contained in or null if there is none.|
|`boolean isDirectory()`|Checks if a File reference is a directory within the file system.|
|`boolean isFile()`|Checks if a File reference is a file within the file system.|
|`long lastModified()`|Returns the number of milliseconds since the epoch (number of milliseconds since 12 a.m. UTC on January 1, 1970) when the file was last modified.|
|`long length()`|Retrieves the number of bytes in the file.|
|`File[] listFiles()`|Retrieves a list of files within a directory|
|`boolean mkdir()`|Creates the directory named by this path.|
|`boolean mkdirs()`|Creates the directory named by this path including any nonexistent parent directories.|
|`boolean renameTo(File dest)`|Renames the file or directory denoted by this path to dest and returns true only if successful|
              
##### Files utility class
// TODO

#### 2. Path
```java
Path.of(String first, String...more)
Path.of(URI uri)
```

##### Paths utility class
// TODO

#### Streams
Java defines two sets of stream classes for reading and writing streams: byte streams and character streams:
* **Byte streams** read/write binary data (0s and 1s) and have class names that end in InputStream or OutputStream.
* **Character streams** read/write text data and have class names that end in Reader or Writer.  

|Class Name|Description|
|---|---|
|`Inputstream`|Abstract class for all input byte streams.|
|`Outputstream`|Abstract class for all output byte streams.|
|`Reader`|Abstract class for all input character streams.|
|`Writer`|Abstract class for all output character streams.|


|Class Name|Parent Class|Low/High Level|Description|
|---|---|---|---|
|`FileInputStream`|`InputStream`|Low|Reads file data as bytes.|
|`FileOutputStream`|`OutputStream`|Low|Writes file data as bytes.|
|`FileReader`|`InputStreamReader <-- Reader`|Low|Reads file data as characters.|
|`FileWriter`|`OutputStreamWriter <-- Writer`|Low|Writes file data as characters.|
|`InputStreamReader`|`Reader`|High|It reads bytes and decodes them into characters.|
|`OutputStreamWriter`|`Writer`|High|Characters written to it are encoded into bytes.|
|`BufferedInputStream`|`FilterInputStream <-- InputStream`|High|Reads byte data from an existing InputStream in a buffered manner, which improves efficiency and performance.|
|`BufferedOutputStream`|`FilterOutputStream <-- OutputStream`|High|Writes byte data to an existing OutputStream in a buffered manner, which improves efficiency and performance.|
|`BufferedReader`|`Reader`|High|Reads character data from an existing Reader in a buffered manner, which improves efficiency and performance.|
|`BufferedWriter`|`Writer`|High|Writes character data to an existing Writer in a buffered manner, which improves efficiency and performance.|
|`ObjectInputStream`|`InputStream`|High|Deserializes primitive Java data types and graphs of Java objects from an existing InputStream.|
|`ObjectOutputStream`|`OutputStream`|High|Serializes primitive Java data types and graphs of Java objects to an existing OutputStream.|
|`PrintStream`|`FilterOutputStream <-- OutputStream`|High|Writes formatted representations of Java objects to a binary stream.|
|`PrintWriter`|`Writer`|High|Writes formatted representations of Java objects to a character stream.|
From which, a _low-level_ stream connects directly with the source of the data, such as a file, an array, or a String; whereas a _high-level_ stream is built on top of another stream using wrapping. Wrapping is the process by which an instance is passed to the constructor of another class, and operations on the resulting instance are filtered and applied to the original instance.

