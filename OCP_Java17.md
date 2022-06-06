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
