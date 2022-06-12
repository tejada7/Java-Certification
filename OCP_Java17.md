# Java 17 Oracle Certified Professional study notes

### Some definitions
- __happens-before relationship:__ it's a guarantee that any action performed in any thread is reflected to other 
actions in different threads. `Stream#forEachOrdered` is an example of executions that comply with this term. 
- __Pattern matching:__ is a technique of controlling program flow that only executes a section of code that meets
certain criteria. Moreover, it's used in conjuction with if statements for greater program control.


### How to print bytes as String
```java
byte b = 4;
String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'))
```

### New text block methods
- `public String indent(int numberSpaces)` → adds/removes the same number of blank spaces of each line. **It's with
noting that if absent, it'll add a new line (\n) to the end of the string.**
- `public String stripIndent()` → It removes all incidental whitespace, but **does not add 
a trailing line break if missing like the above method.**

Example:
```java
final var greeting = " hola\n"
        .concat("  hello\n")
        .concat(" bonjour.");

greeting.indent(-1)/**
                    *hola\n
                    * hello\n
                    *bonjour\n
                    */

greeting.indent(-2)/**
                    *hola\n
                    *hello\n
                    *bonjour\n
                    */

greeting.indent(0) /**
                    * hola\n
                    *  hello\n
note the \n added → * bonjour\n
                    */

greeting.stripIndent()/**
                       *hola\n
                       * hello\n
  \n not added !!! →   *bonjour
                       */
```

### String comparison
Strings in Java are naturally ordered (i.e. ascending from smaller to bigger) in the following order:
1. Numbers 
2. `CharSequences` in capital letters 
3. `CharSequences` in small letters 

e.g.
Given
```java
String[] array = {"abc", "T", "TOTO", "123", "1", "2", "_1_2", "xyz", "a", "x", "z", "y", "xy", "b", "c", "d", "ab", "ba", "ac", "ca"};
Arrays.sort(array); // It'll become: [1, 123, 2, T, TOTO, _1_2, a, ab, abc, ac, b, ba, c, ca, d, x, xy, xyz, y, z]
```

### Time API notes

- `ZonedDateTime` contains an `OffSetDateTime` as well as a Zone. However, both types can be initialized as:

```java
OffsetDateTime.now(ZoneId.of("America/La_Paz")) // 2022-06-06T14:12:06.294197-04:00
ZonedDateTime.now(ZoneId.of("America/La_Paz")) // 2022-06-06T14:12:06.294197-04:00[America/La_Paz]
```
One fundamental difference relies on the fact that `ZonedDateTime` allows daylight saving calculations:
```java
// Given the US's spring forward:
ZonedDateTime.of(LocalDateTime.parse("2022-03-13T01:30:00"), ZoneId.of("America/Los_Angeles"))
        .plusHours(1) // 2022-03-13T03:30-07:00[America/Los_Angeles]
        
// Whereas
OffsetDateTime.of(LocalDateTime.parse("2022-03-13T01:30:00"), ZoneOffset.of("-08:00"))
        .plusHours(1) //2022-03-13T02:30-08:00

// It's worth noting that CEST timezone is not accepted in Java, instead always use CET:
ZonedDateTime.of(LocalDateTime.parse("2022-03-27T01:30:00"), ZoneId.of("CET"))
        .plusHours(1) // 2022-03-27T03:30+02:00[CET]
```
- `DateTimeFormatter` contains 4 `FormatStyles`:
  given

```java
DateTimeFormatter
        .ofLocalizedDate(formatStyle)
        .withLocale(new Locale.Builder().setLanguageTag(locale).build())
        .format(Instant.now())
```
  where formatStyle and locale are:

| FormatStyle | locale es-BO              | locale en-US | locale fr-FR|
|-------------|---------------------------|--------------|--------------|
| SHORT       | 6/6/2022                  | 2022-06-06   | 06/06/2022             |
| MEDIUM      | 6 jun. de 2022            | Jun 6, 2022  | 6 juin 2022             |
| LONG        | 6 de junio de 2022        | June 6, 2022 | lundi 6 juin 2022             |
| FULL        | lunes, 6 de junio de 2022 | Monday, June 6, 2022        | lundi 6 juin 2022             |

- UTC uses the same time zone zero as GMT, therefore they are equivalent
- Z at the end of some dates means Zulu or zero timezone (not to be confused with 0 offset)
- It's possible to truncate some parts of the time:
```java
// Here we ignore the minutes
LocalTime.of(1, 10)
        .truncatedTo(ChronoUnit.HOURS) // 01:00
```
### StringBuilder notes
- The replace method from `StringBuilder` takes 3 arguments:
```java
StringBuilder replace(int start, int end, String str) {
```
whereas the one from `String` comes in two flavors:
```java
String replace(char oldChar, char newChar)
String replace(CharSequence target, CharSequence replacement)
```
### Method overloading order

Given the methods:
```java
void doStuff(int i) {...}
void doStuff(long l) {...}
void doStuff(Integer i) {...}
void doStuff(int...args) {...}
```
The below table shows the order by which Java resolves a method when overloaded. 

| Rule                  |
|-----------------------|
| exact match by type   |
| larger primitive type |
| autoboxing            |
| vargs                 |

### Class initialization order
1. Parent class static content (i.e. steps 2 to 3)
2. static variable declaration order-wise
3. static blocks order-wise
4. if there's a superclass, initialize it first (i.e. steps 5 to 7)
5. instance variable declaration order-wise
6. instance block order-wise
7. initialize constructor

example:
```java
class Parent {
  static {
    System.out.print("A"); // 1
  }

  {
    System.out.print("B"); // 3
  }

  public Parent(String name) {
    this(1);
    System.out.print("C"); // 5
  }

  public Parent() {
    System.out.print("D");
  }

  public Parent(int stripes) {
    System.out.print("E"); // 4
  }
}

class Child extends Parent {
  static {
    System.out.print("F"); // 2
  }

  {
    System.out.print("H"); // 6 
  }

  public Child(int stripes) {
    super("");
    System.out.print("G"); // 7
  }

  public static void main(String[] grass) {
    new Child(1); // AFBECHG
  }
}
```
### Rules for overriding a method
1. The method in child must have the same signature (i.e. name + args) as the parent's one
2. The method in child must be at least as accessible as the method in the parent class
3. The method in child may not declare any exceptions (including checked, or else it cannot be broader than the checked 
exception of the parent)
4. The child return type must be **covariant** i.e. the overriding return type should be assignable to the parent return
type without a cast

### Variable and method hiding
Java does not allow variables to be overriden, they can be hidden though. They are resolved based on the type of the
reference and **not the instance type** conversely to what occurs with polymorphic method calls.

Likewise, static methods can be overriden if the parent's method is not declared as final.  

### Sealing classes
_Why do we need sealed classes?_ 
It strengthens domain modeling by allowing closed class hierarchy representation. It ultimately enhances the new Java 
pattern matching feature with switch statement.   

Some new keywords:
- `sealed` → indicates that a class (concrete or abstract) or interface may only be extended/implemented by named classes
or interfaces.
- `permits` → used along with `sealed`, lists the classes/interfaces that can extend/implement it.
It's usage depends on the location of the subclasses:
  - In a different package → required
  - In the same file as the sealed class → not required, but permitted.
  - Nested class of the sealed class → not required, but permitted.
- `non-sealed` → applies to any class/interface extending/implementing the sealed class, indicating that it can be extended/
implemented by unspecified classes.

Direct subclasses of sealed classes must be marked final, sealed or non-sealed (excluding for interfaces for which they 
can be defined as either sealed or non-sealed).

### Records compact constructor
Given:
```java
record Person(String fullName, int age) {}
```
We can leverage compact constructors to process (i.e. validate or alter) constructor arguments before the actual
constructor is invoked: 
```java
record Person(String fullName, int age) {
    
    Person { // Mind that () aren't required, nor the list of arguments
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("fullname invalid format.");
        }    
        age++; // Allowed, but discouraged !!!
    }
}
```
### Lambda expressions & functional interfaces
Lambdas aren't allowed to redeclare local variables. They're only allowed to reference final or effectively final
variables.
```java
void doStuff() {
    var var = "foo";
    final var var1 = "foo";
    // String p = ""; // If this line would be uncommented out, then the below line wouldn't compile
    Predicate<String> p = string -> {
        String var1 = ""; // not allowed
        print(var); // allowed if and only if var is effectively final    
        return true;
    };
}
```
Functional interfaces must only have one abstract class excluding any redefining method of the class Object (i.e. 
`toString`, `hashCode`, `equals`, etc).
