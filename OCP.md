# Java 11 Oracle Certified Professional study notes

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
