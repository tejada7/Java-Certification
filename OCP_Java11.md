# Java 11 Oracle Certified Professional study notes

### Inner classes
* Static nested class (or inner records)
    * Can be instantiated without an instance of the enclosing class
    * It cannot access instance variables or methods of the outer class directly, it needs an explicit reference to the outer class.
    * The enclosing class can refer to the static fields and methods of the inner class
```java
package parent;
public class Parent {
    private String name;
    public static final String CONSTANT = "";

    public static class InnerStaticClass { // This can alternatively be a record (no need for static though)
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
        new Parent.InnerClass().instanceMethod();
    }   
```
* Member inner class (aka inner class)
* Anonymous class
* Local class.- Can be defined within methods, constructors and initializers.

### Accessing instance and static content
Given:
```java
class Parent {

    static constant = "123";
    String name = "parent";
    
    String getName(){ return name;}
}
```
And:
```java
class Child extends Parent {

    static constant = "456";
    String name = "child";
    
    String getName(){ return name}
}
```
, accessing static and instance fields as well as static methods depends on the class of reference variable and not the
actual object to which the variable points to. Mind that this is opposite of what happens in the case of instance methods.
In case of instance methods, the method of the actual class of the object is called. e.g.
```java
public static void main (String...args) {
    final var parent = new Parent();
    new StringJoiner(",")
        .add(parent.name)
        .add(parent.constant)
        .add(parent.getName()); // will contain "parent, 123, parent"

    final Parent child = new Child(); // Mind the reference type !
    new StringJoiner(",")
        .add(child.name)
        .add(child.constant)
        .add(child.getName()); // will contain "parent, 123, child"

    final Child child1 = new Child();
    new StringJoiner(",")
        .add(child1.name)
        .add(child1.constant)
        .add(child1.getName()); // will contain "child, 456, child"
}
```
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

That said, *reifiable types* can do anything that Java allows. Non-reifiable types have some limitations, generics are non-reifiable
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

> [!NOTE]
> Note that lower bound (i.e. `super`) cannot be used alongside bounded generic types:
> ```java 
> <T super CharSequence> Collection<T> foo(Collection<T> input){...} // Does not compile
> // <T extends CharSequence> would've compiled
>```

### Rules for identifying correct overriding of generic-return-style methods

Given `T` as type (i.e. class, enum or interface) and `S` and its subtype, `A` being any type for the rules below, we
use <<< to mean *is a subtype of*, therefore, we can state that:
```
S <<< T
A<S> <<< A<? extends S> <<< A<? extends T>
A<T> <<< A<? super T> <<< A<? super S>
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

### Copying Lists
* `List.of()` accepts either multiple individual parameters or an array. If it's a collection though, it'll be treated
as a regular object, e.g.
```java
var original = List.of(1, 2, 3);
var copyList = List.of(original); // It'll become a single-element list of list List<List<Integer>>
```  
List.of() creates an immutable collection, meaning any attempt to modify it will throw an UnsupportedOperationException 
* `List.copyOf()` accepts only a collection and creates an immutable list too. 

**N.B None of the above methods accept null values, throwing NullPointerException otherwise.**
* `Collections.unmodifiableList()` creates an unmodifiable view of a list and does not protect the underlying list from
being modified. Besides, this method allows null elements.  

> [!NOTE]
> ℹ️ If the original list is updated, its change will be reflected in the view

### Streams
> With streams, data isn't generated up front. It gets executed only when needed. 

>This is an example of lazy evaluation, which delays execution until necessary.
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
  
      Arrays.stream(new String[]{}).reduce(String::concat); // Optional.empty
    ```
* `T reduce(T identity, BinaryOperator<T> accumulator)` -> identity is the starting value so that the accumulator sequentially merges other T Stream elements and returns a T
    ```java
        List.of("h", "o", "l", "a").stream().reduce("¡" ,String::concat); // "¡Hola"
  
        Arrays.stream(new String[]{}).reduce("¡", String::concat); // "¡"
    ```
* `U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner)` -> identity is the starting value so that the accumulator sequentially transforms T to U, the combiner is only used for parallel streaming, and its purpose is to combine preliminary results.  
    ```java
        List.of("h", "o", "l", "a").stream().reduce(0, (identity, streamElement) -> identity + streamElement.length(), Integer::sum); // the combiner does not do anything here and the result is 4
 
        List.of("h", "o", "l", "a").stream().parallel().reduce(0, (identity, streamElement) -> identity + streamElement.length(), Integer::sum); // the result is 4 and the performance is better
    ```

#### Collect method
* `<R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner)` -> supplier is the function that creates the result container so that the accumulator can fold elements to the result container; finally, the combiner combines the partial result containers in the event it is a parallel stream.  
    ```java
        List.of("h", "o", "l", "a").stream()
            .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append); // the result will be a string builder with the greeting hola, it's worth noting that combines does nothing, unlike the below example: 
 
        List.of("h", "o", "l", "a").stream().parallel()
            .foreach(System.out::print); // non-deterministic output, it could be any combination
  
        // ℹ️ mind that when using collectors, the order depends on the spliterator characteristics of the underlying list 
        List.of("h", "o", "l", "a").stream().parallel()
            .collect(ArrayList::new, List::add, List::addAll); // will still be List<h,o,l,a> because the list spliterator reports ORDERED characteristic
    ```

> [!IMPORTANT]  
> `short-circuiting stateful intermediate operations` are methods that given an **infinite input** (e.g. `Stream.generate(...)`, may terminate in **finite time**. Some examples:
> - `allMatch(Predicate)`
> - `anyMatch(Predicate)`
> - `noneMatch(Predicate)`
> - `takeWhile(Predicate)`
> - `findAny()`
> - `findFirst()`

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

|Method Name| Description                                                                                                                                                       |
|---|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|
|`boolean delete()`| Deletes the file or directory and returns true only if successful. If this instance denotes a directory, then the directory must be empty in order to be deleted. |
|`boolean exists()`| Checks if a file or directory exists.                                                                                                                             |
|`String getAbsolutePath()`| Retrieves the absolute name of the file or directory within the file system.                                                                                      |
|`String getName()`| Retrieves the name of the file or directory.                                                                                                                      |
|`String getParent()`| Retrieves the parent directory that the path is contained in or null if there is none.                                                                            |
|`boolean isDirectory()`| Checks if a File reference is a directory within the file system.                                                                                                 |
|`boolean isFile()`| Checks if a File reference is a file within the file system.                                                                                                      |
|`long lastModified()`| Returns the number of milliseconds since the epoch (number of milliseconds since 12 a.m. UTC on January 1, 1970) when the file was last modified.                 |
|`long length()`| Retrieves the number of bytes in the file.                                                                                                                        |
|`File[] listFiles()`| Retrieves a list of files within a directory                                                                                                                      |
|`boolean mkdir()`| Creates the directory named by this path.                                                                                                                         |
|`boolean mkdirs()`| Creates the directory named by this path including any nonexistent parent directories.                                                                            |
|`boolean renameTo(File dest)`| Renames the file or directory denoted by this path to dest and returns true only if successful                                                                    |
              
##### File utility classes

This utility class operates only on Path instances, and not on File ones.

| Enum type            | Interface inherited   | Enum value        | Details                                                                     |
|----------------------|-----------------------|-------------------|-----------------------------------------------------------------------------|
| `LinkOption`         | CopyOption OpenOption | NOFOLLOW_LINKS    | Do not follow symbolic links.                                               |
| `StandardCopyOption` | CopyOption            | ATOMIC_MOVE       | Move file as atomic file system operation.                                  |
|                      |                       | COPY_ATTRIBUTES   | Copy existing attributes to new file.                                       |
|                      |                       | REPLACE_EXISTING  | Overwrite file if it already exists.                                        |
| `StandardOpenOption` | OpenOption            | APPEND            | If file is already open for write, then append to the end.                  |
|                      |                       | CREATE            | Create a new file if it does not exist.                                     |
|                      |                       | CREATE_NEW        | Create a new file only if it does not exist, fail otherwise.                |
|                      |                       | READ              | Open for read access.                                                       |
|                      |                       | TRUNCATE_EXISTING | If file is already open for write, then erase file and append to beginning. |
|                      |                       | WRITE             | Open for write access.                                                      |
| `FileVisitOption`    | N/A                   | FOLLOW_LINKS      | Follow symbolic links.                                                      |

|                                                   |                                                       |
|---------------------------------------------------|-------------------------------------------------------|
| `boolean exists(Path, LinkOption…)`               | `Path move(Path, Path, CopyOption…)`                  |
| `boolean isSameFile(Path, Path)`                  | `void delete(Path)` (*)                               |
| `Path createDirectory(Path, FileAttribute<?>…)`   | `boolean deleteIfExists(Path)` (*)                    |
| `Path createDirectories(Path, FileAttribute<?>…)` | `BufferedReader newBufferedReader(Path)`              |
| `Path copy(Path, Path, CopyOption…)`              | `BufferedWriter newBufferedWriter(Path, OpenOption…)` |
| `long copy(InputStream, Path, CopyOption…)`       | `List<String> readAllLines(Path)`                     |
| `long copy(Path, OutputStream)`                   | `Stream lines(Path)`                                  |

(*) Only applicable to files and empty directories, otherwise use:
```java
try (var stream = Files.walk(Path.of("aDirectory"))) {
    stream.forEach(Unchecked.consumer(Files::delete));
}
// or if you're using Spring, you can leverage FileSystemUtils#deleteRecursively
```
#### 2. Path
A Path instance represents a hierarchical path on the storage system to a file or directory. You can think of a Path as the NIO.2 replacement for the java.io.File class, although how you use it is a bit different.

Unlike the `java.io.File`, the `Path` interface contains support for _symbolic links_. 
```java
Path.of(String first, String...more)
Path.of(URI uri)
```

|                                                                                                                                                    |                                |
|----------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------|
| `Path of(String, String…)`                                                                                                                         | `Path getParent()`             |
| `URI toURI()`                                                                                                                                      | `Path getRoot()`               |
| `File toFile()`                                                                                                                                    | `boolean isAbsolute()`         |
| `String toString()`                                                                                                                                | `Path toAbsolutePath()`        |
| `int getNameCount()`                                                                                                                               | `Path relativize()`            |
| `Path getName(int)` <br/> → indices start from 0, <br/> → root excluded from the path (e.g. `c:\` or `/`),<br/> → invalid paths throw an exception | `Path resolve(Path)`           |
| `Path subpath(int, int)`                                                                                                                           | `Path normalize()`             |
| `Path getFileName()`                                                                                                                               | `Path toRealPath(LinkOption…)` |

> [!NOTE]  
> Some important reminders about `Path#resolve` and `Path#relativize` methods:
> - `relativize` is meant to transform an absolute path into a relative
> - `path1.relativize(path1.resolve(p2)) == p2`
> - `path1.resolve(path2)` will return path2 is this last one references an absolute path (e.g. `Path.of("foo/1").resolve(Path.of("/absolute/2))` → `/absolute/2` ) 
> - `path1.relativize(path2)` will throw an `IllegalArgumentException` if path1 and path2 are mixed between absolute and relative paths
> - `Path#relativize` normalizes the paths before computation

> [!NOTE]  
> Some important reminders about Paths
> - Index starts from 0
> - Root (i.e. `\` or `c:\`) is not considered as index 0
> - Paths do not start nor end with `\` with the exception of Unix root `\`

##### Paths factory class
```java
Paths.get(String first, String...more)
Paths.get(URI uri)
```

#### 3. DirectoryStream
Allows to to iterate over the entries of a directory, there is a constructor variation that takes a glob pattern, example:
```java
// Filters out all files with the extensions .java or .class 
try(DirectoryStream<Path> stream = Files.newDirectoryStream(Path.of("."), "*.{java,class}")) {
  for (var path : stream) { // it implements Iterable
  ...
  }
}
```

#### IO Streams
Java defines two sets of stream classes for reading and writing streams: byte streams and character streams:
* **Byte streams** read/write binary data (0s and 1s) and have class names that end in InputStream or OutputStream.
* **Character streams** read/write text data and have class names that end in Reader or Writer.  

|Class Name|Description|
|---|---|
|`Inputstream`|Abstract class for all input byte streams.|
|`Outputstream`|Abstract class for all output byte streams.|
|`Reader`|Abstract class for all input character streams.|
|`Writer`|Abstract class for all output character streams.|

Most important implementations: 

- Reader: FileReader (mark not supported), StringReader (mark supported), CharArrayReader (mark supported)

| Class Name                               | Parent Class                          | Low/High Level | Description                                                                                                                                              |
|------------------------------------------|---------------------------------------|----------------|----------------------------------------------------------------------------------------------------------------------------------------------------------|
| `FileInputStream`                        | `InputStream`                         | Low            | Reads file data as bytes.                                                                                                                                |
| `FileOutputStream`                       | `OutputStream`                        | Low            | Writes file data as bytes.                                                                                                                               |
| `FileReader`                             | `InputStreamReader <-- Reader`        | Low            | Reads file data as characters. It throws a FileNotFoundException if the file does not exist                                                              |
| `FileWriter`                             | `OutputStreamWriter <-- Writer`       | Low            | Writes file data as characters. It creates a file if it does not exist in the filesystem                                                                 |
| `InputStreamReader`                      | `Reader`                              | High           | It reads bytes and decodes them into characters.                                                                                                         |
| `OutputStreamWriter`                     | `Writer`                              | High           | Characters written to it are encoded into bytes.                                                                                                         |
| `BufferedInputStream`                    | `FilterInputStream <-- InputStream`   | High           | Reads byte data from an existing InputStream in a buffered manner, which improves efficiency and performance.                                            |
| `BufferedOutputStream`                   | `FilterOutputStream <-- OutputStream` | High           | Writes byte data to an existing OutputStream in a buffered manner, which improves efficiency and performance.                                            |
| `BufferedReader`                         | `Reader`                              | High           | Reads character data from an existing Reader in a buffered manner, which improves efficiency and performance.                                            |
| `BufferedWriter`                         | `Writer`                              | High           | Writes character data to an existing Writer in a buffered manner, which improves efficiency and performance.                                             |
| `ObjectInputStream` / `DataInputStream`  | `InputStream`                         | High           | Deserializes primitive Java data types and graphs of Java objects from an existing InputStream.  ℹ️ This class exposes readUTF only to read strings.     |
| `ObjectOutputStream`/ `DataOutputStream` | `OutputStream`                        | High           | Serializes primitive Java data types and graphs of Java objects to an existing OutputStream.  ℹ️ This class exposes the writeUTF and writeChars methods. |
| `PrintStream`                            | `FilterOutputStream <-- OutputStream` | High           | Writes formatted representations of Java objects to a binary stream. Mind that its methods **do not throw IOException**.                                 |
| `PrintWriter`                            | `Writer`                              | High           | Writes formatted representations of Java objects to a character stream.                                                                                  |
From which, a _low-level_ stream connects directly with the source of the data, such as a file, an array, or a String; whereas a _high-level_ stream is built on top of another stream using wrapping. Wrapping is the process by which an instance is passed to the constructor of another class, and operations on the resulting instance are filtered and applied to the original instance.

The below table compares the legacy `java.io.File` vs. the `NIO.2` methods:

|Legacy I/O File method|NIO.2 method|
|---|---|
|`file.delete()`|`Files.delete(path)`|
|`file.exists()`|`Files.exists(path)`|
|`file.getAbsolutePath()`|`path.toAbsolutePath()`|
|`file.getName()`|`path.getFileName()`|
|`file.getParent()`|`path.getParent()`|
|`file.isDirectory()`|`Files.isDirectory(path)`|
|`file.isFile()`|`Files.isRegularFile(path)`|
|`file.lastModified()`|`Files.getLastModifiedTime(path)`|
|`file.length()`|`Files.size(path)`|
|`file.listFiles()`|`Files.list(path)`|
|`file.mkdir()`|`Files.createDirectory(path)`|
|`file.mkdirs()`|`Files.createDirectories(path)`|
### Modules
It's important to understand what problems are modules designed to solve:
- Reinforced security → vulnerable packages can be omitted.
- Clearer dependency management → avoid jar hell or confusion by ensuring that each package comes from only one module   
- Better performance → improved startup time and less memory consumption
- Better access control → it resembles to a multi-Maven-module approach that adds an additional layer of access control  
- Custom Java build → by restricting the number of the internal jdk dependencies that ultimately reduces jar sizing   

That said, there are three types of modules:
- named → they contain a module-info.java file, they reside in the module path (and not in the classpath). Named modules 
 can only read from automatic modules.
- automatic → do not contain module-info.java file but do reside in the module path, so that it gets treated as a module.
As far as its name, it'll be resolved from the MANIFEST.MF file, property `Automatic-Module-Name` if specified, otherwise
Java will automatically determine its name. Automatic modules can read from unnamed modules (i.e. jars in the classpath) 
as well as the explicitly exported modules from the named modules.
- unnamed → reside in the classpath rather that the module path, thus, they aren't treated as modules per se. 
**They can read from any jars on the class or module paths.** They do not export any package, thus only readable 
from the classpath and by automatic or other unnamed modules.

Given the structure:
```shell script
.
├── src
    ├── module-info.java
    └── com
          └── ocp
                └── hello
                      └── Main.java
```
And the content of `module-info.java`:
```java
module com.ocp.hello {} // Module names should usually match package names 
```
> [!NOTE]
> Modules can be empty but must at least define its name

Compiling the old-fashioned way (with classpath):
```shell script
javac -d out src/com/ocp/hello/Main.java 
```
will create binary files on the `out` directory:
```shell script
.
├── src
│   ├── module-info.java
│   └── com
│         └── ocp
│               └── hello
│                     └── Main.java
└── out
    └── com
          └── ocp
                └── hello
                      └── Main.class
```
To run the `Main` class:
```shell script
java -cp out com.ocp.hello.Main.java 
```
Compiling the module:
```shell script
javac -d out src/com/ocp/hello/Main.java src/module-info.java 
```
It will create the structure:
```shell script
.
├── src
│   ├── module-info.java
│   └── com
│         └── ocp
│               └── hello
│                     └── Main.java
└── out
    └── com
     │   └── ocp
     │         └── hello
     │              └── Main.class
     └────── module-info.class
```
to Run:
```shell script
java -p out -m com.ocp.hello/com.ocp.hello.Main
or 
java --module-path <module path> --module <module name>/<fully qualified class name>
or
java --show-module-resolution --module-path <module path> --module <module name>/<fully qualified class name>
```
Alternatively, we can restructure the program as:

```shell script
.
└── src
    └── com.ocp.hello 
                  ├── module-info.java
                  └── com
                      └── ocp
                          └── hello
                              └── Main.java
```
And compile the module with:
```shell script
javac -d out --module-source-path src -m com.ocp.hello 
```
And finally run with the same command:
```shell script
java -p out -m com.ocp.hello/com.ocp.hello.Main 
or
java --module-path <module path> --module <module name>/<fully qualified class name>
```
For **compiling** Java files using modules, the following command-line options are available:
* `--module-source-path`: location of the module(s) sources files, it has to point to a parent directory where all
module-info files are stored, note that if we have module called foo, then a directory foo must be present in this
parent directory (usually src). If more directories are available, then they can be separated with semicolon
(--module-source-path src;src1).
* `-d`: option to specify the output directory of the compiled files, it's always required independently of using modules.
For running the program the old way, the `-cp` option must point to the same folder and if running a modular program, the option
`-p` must be used instead. 
* `--module or -m`: Use only with `--module-source-path`. It's useful to compile all classes in a module at once without
listing them out. 
* `--module-path or -p` (also used for running with java command): This option specifies the location(s) of any other
module dependency. You can specify the exploded module directories, directories containing modular jars, or specific 
modular jars here. e.g.
If you want to compile module foo and it depends on another module named abc.util packaged as utils.jar located in
thirdpartymodules directory then your module-path can be thirdpartymodules or thirdpartymodules/utils.jar.
The following commands would equally work:
```shell script
javac --module-source-path src --module-path thirdpartymodules -d out --module foo 
or
javac --module-source-path src --module-path thirdpartymodules/utils.jar -d out --module foo 
``` 

*N.B If we need to add a non-modular third party jar, we have to add the jar to the `--module-path` so that it'll be
loaded as an automatic-module, then in the application requiring the third-party jar,
we add requires <automatic-module-name> in the module-info.java*

There are three command line options applicable to javac and java that can be used for customizing exports and requires
configurations of modules temporarily. 
These are: `add-reads`, `add-exports`, and, `add-open`. For example, if you want module1 to be able to read public
packages of module2 and neither of the modules have appropriate information in their respective module-info files, then
you can use the following commands to enable such access:  
```shell
javac --add-reads module1=module2 --add-exports module2/com.ocp.package1=module1 

java --add-reads module1=module2 --add-exports module2/com.ocp.package1=module1
```
`--add-reads module1=module2` implies that module1 wants to read all exported packages of module2.
`--add-exports module2/com.ocp.package1=module1` implies that module2 exports package com.ocp.package1 to module1.

> [!IMPORTANT]
> It's still possible to force the jvm to load modules that aren't explicitly required in the main module, this is 
> done via the --add-modules flag:
> ```shell
> java --module-path main-module.jar;automatic-module.jar 
>      --add-modules automatic-module # this adds requires automatic-module to main-module at run time
>      --module main-module/com.example.Main
> ```

To describe the module:
```shell
java -p <module path> -d <module name>
or
java -p <module path> --describe-module <module name>
```
And to list the modules:
```shell
java -p <module path> --list-modules
```
> [!NOTE]
> Modules don't allow circular dependencies (i.e. module A requires module B and vice versa)

> [!IMPORTANT]
> The Java Platform Module System uses three phases for building an app:
> - compile time → Source code is compiled into bytecode (.class files or modular JARs) using javac.
> - link time [optional] → The jlink tool resolves module dependencies, optimizes the code, and packages it into a custom runtime image.
> - run time → The custom runtime image is executed

### Service provider
Given a module exporting an interface:
```java
package api.usecases;
public interface UseCase {
    Foo doSomething(Args args);
}
```

```java
module api {
    exports api.usecases;
}
```

And a module providing a service:
```java
module foo {
    requires api;
    provides api.usescases.UseCase with foo.DefaultUseCase;  
}
```

> [!IMPORTANT]
> A module can only provide one SPI at most per service 

`DefaultUseCase` must either:
- implement the interface, and **define a public no-arg constructor**:
```java
package foo;
import api.usecases.UseCase;

public DefaultUseCase implements UseCase {
    
    // ℹ️ default no-arg public constructor
    
    public Foo doSomething(Args args) {
        // implementation
    }
}
```
or,
- define a public static provider method: 
```java
package foo;
import api.usecases.UseCase;

DefaultUseCase { // ℹ️ No need to implement service
    
    public static UseCase provider() {
        return (args) -> {
            // implementation
            return Foo...
        };
    }
}
```

Services can then be retrieved using `ServiceLoader#load`

### jdeps
As a reminder on how to create a jar:
```shell
jar -cvf <output directory>/<jar file>.jar <binaries directory>/ .
```
If we need to define a Main class, then we must create a `.mf` file with the below content:
```text
Manifest-Version: 1.0
Main-Class: packageToMainClass.Main
```
then to build the jar reference with the above-created file:
```shell
jar cmf manifest-file.mf jar-file.jar input-files
```
Then, to run it:
```shell
java -jar jar-file-with-main.jar
# or
java -p jar-file-with-main.jar module/package.Main
```
Then to display a summary of the modules used in the jar:
```shell
jdeps -s <jar file>.jar
or
jdeps --summary <jar file>.jar
```
Or the list of all modules on which the underlying module depends
```shell
jdeps --list-deps module.jar # mind that it'll display and error if the module requires other non standard module
```

Alternatively, we can get the dependencies of a specific class:
```shell
jdeps --module-path out out/<module name>/<fully qualified class name>
```
It's also possible to detect any unsupported or for-removal internal jdk dependency
```java
jdeps --jdk-internals -module-path out <jar file>.jar 
```
### jmod
jmods are file extensions only recommended when we have native libraries that cannot be stored in jar files. That said,
jars are the recommended format for modules.
The below example displays the required modules by the ma.jmod file:
```shell
jmod describe jmods/ma.jmod
```
jmods allows to: `create`, `list`, `describe`, `extract`, `hash`
### javap
Allows to disassemble .class files, displaying information about fields, contructors and methods.
```shell
javap -v -p -l <class name>.class
```
Wherein: `v` means verbose, `p` allows to print private fields and methods and `l` displays the line numbers. 
### jlink
Allows to create Java runtimes without needing the entire JDK to execute it.
```java
jlink -p mods --add-modules <module-name> --output <destination-folder-of-runtime>
```
### Serialization
The class must implement the `Serializable` interface. Unless explicitly provided, the compiler will automatically generate 
the constant `serialVersionUID`, its value is computed based on the attributes of the class.

> [!IMPORTANT]  
> `serialVersionUid` constant is useful to historize the object serialized versions, it's generated based on the fields and implemented interfaces. 

> [!NOTE]
> To check the value generated by the compiler, we can run:
> - using JDK's `serialver fullyQualifiedJavaClassName`, example: `serialver -classpath classes/ com.ftm...ClassName`, or
> - `ObjectInputStream#readClassDescriptor()` method as in new `ObjectInputStream(new FileInputStream(foo.bin)).readClassDescriptor()`


### Deserialization
When deserializing an object, the constructor of the serialized class, along with any instance initializers, is not
called when the object is created. Java will however call the no-arg constructor of the first nonserializable **parent
class** it can find in the class hierarchy.
Any static or transient fields are ignored. Values that are not provided will be given their default Java value, such as null for String, or 0 for int values.

> [!IMPORTANT]  
> This rule does not apply to record, whose canonical constructor is always called during deserialization

When defining the `readObject` method (which is executed before actual data deserialization), it's possible to force the serializable fields initialization by calling 
`ObjectInputStream#defaultReadObject` method

```java
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.LocalDate;

class Data extends Parent implements Serializable {
    int id = 12;
    static String name = "john";
    transient Localdate birthDate = LocalDate.EPOCH;

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        System.out.println("before: " + this); // before: [id=0, name=null, birthDate=null, nonSerializableObject=initialized]
        //assuming there is a serialized object with id=66;
        stream.defaultReadObject();
        System.out.println("after: " + this); // after: [id=66, name=null, birthDate=null, nonSerializableObject=initialized]
    }

    @Override
    public String toString() {
        return "[id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", " + super.toString() + "]";
    }
}

class Parent {
    Object nonSerializableObject;

    Parent() {
        nonSerializableObject = "initialized";
    }

    @Override
    public String toString() {
        return "nonSerializableObject=" + nonSerializableObject;
    }
}
```

### Concurrency
* **Liveness** - Ability of an application to be able to execute in a timely manner. Liveness problems, then, are those
in which the application becomes unresponsive or in some kind of "stuck" state.
* **Deadlock** - Situation where two or more threads are blocked **forever**, waiting for each other.
* **Livelock** - Occurs when two or more threads are conceptually blocked forever, although they are each still active
and trying to complete their task. 
* **Starvation** - Occurs when a single thread is perpetually denied to access to a shared resource or lock. The thread is
still active, but it is unable to complete its work as a result of other threads contanstly taking the resource that
they are trying to access.
### Suppressed Exceptions
They usually occur when using try-with-resources when an exception is thrown when closing the resource. However, it can
also occur in any context by leveraging the methods `Throwable#addSuppressed` and `Throwable#getSuppressed`.
It's important to mention that a method can **only throw one exception**. For example:
```java
void method() {
    try {
        //doSomething
    } catch(Exception e) {
        throw new RuntimeException("Exception from catch block");
    } finally {
        throw new RuntimeException("Exception from finally block");
    }
}
```  
Only the exception from finally block will be thrown and the exception from the catch block will be **lost** (i.e. not
even considered as a suppressed exception).

Now, as per the actual suppressed exceptions, only the first thrown will be at the root level, leaving the ones thrown 
in close method as suppressed:

```java
import java.util.Arrays;

class Foo implements AutoCloseable {

    @Override
    public void close() throws Exception {
        throw new RuntimeException("exception from close");
    }

    public static void main(String[] args) {
        try (var foo = new Foo()) {
            throw new RuntimeException("first exception");
        } catch (Exception e) {
            System.out.print("Top-level exception: " + e.getMessage() +
                ", Suppressed exceptions: " + printSuppressedExceptions(e)); // Top-level exception: first exception, Suppressed exceptions:exception from close   
        }
    }

    private static String printSuppressedExceptions(Exception e) {
        return Arrays.stream(e.getSuppressed()).map(Throwable::getMessage).collect(Collectors.joining("\n"));
    }
}
```
### Multiple inheritance of state
One reason why the Java programming language does not permit you to extend more than one class is to avoid the issues of
multiple inheritance of state, which is the ability to inherit fields from multiple classes. For example, suppose that
you are able to define a new class that extends multiple classes. 
When you create an object by instantiating that class, that object will inherit fields from all of the class's
superclasses. What if methods or constructors from different superclasses instantiate the same field? Which method or
constructor will take precedence? Because interfaces do not contain fields, you do not have to worry about problems that
result from multiple inheritance of state.
> *Remember that member variables are hidden and not overridden.*
### Multiple inheritance of implementation
It is the ability to inherit method definitions from multiple classes. Problems arise with this type of multiple
inheritance, such as name conflicts and ambiguity.  Default methods introduce one form of multiple inheritance of
implementation. A class can implement more than one interface, which can contain default methods that have the same
name. The Java compiler provides some rules to determine which default method a particular class uses.
### Multiple inheritance of type
It is the ability of a class to implement more than one interface.
### Deque
|Method|description|
|---|---|
|`add`|Adds an element to the end of the collection (same as the add from `List`), it throws an exception is no capacity is available|
|`addLast`|Same as the above|
|`addFirst`|Adds an element to the head of the collection|
|`offer`|Adds an element to the end of the `Queue`|
|`offerLast`|Same as the above|
|`offerFirst`|Adds an element to the head of the queue|
|`remove`|Removes an element from the head of the collection and throws an exception is elements are present|
|`removeFirst`|Same as the above|
|`removeLast`|Removes an element from the end of the collection|
|`poll`|Removes an element from the head of the `Queue`|
|`pollFirst`|Same as the above|
|`pollLast`|Removes an element from the tail of the `Queue`|
|`peek`|Reads an element from the head of the `Queue`|
|`peekFirst`|Same as the above|
|`peekLast`|Reads an element from the tail of the `Queue`|
|`push`|Appends an element to the front of the `Stack`|
|`pop`|Removes an element from the head of the `Stack`|

### Optional#or method
In some situations, we're required to combine multiple optionals:
```java

sealed interface Entity {}
record UserAccount() implements Entity {}
record AdminAccount() implements Entity {}
    
interface UserRepository {
    Optional<UserAccount> getById(UUID id);
}

interface AdminRepository {
    Optional<AdminAccount> getById(UUID id);
}

record Service(
        UserRepository userRepository,
        AdminRepository adminRepository
) {
    public Entity resolveEntity(UUID id) {
        return userRepository.getById(id)
                .or(() -> adminRepository.getById(id))
                .orElseThrow();
    }    
}
```
From where:

`.or(() -> adminRepository.getById(id))` → gets called if and only if the former optional is not empty.
