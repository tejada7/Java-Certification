# Java 21 Oracle Certified Professional study notes

### Java basic reminders

- method's **local variables** are also known as _automatic variables_ because they cease to exist as soon as the execution of the block in which they were defined completes
```java
public void foo() {
    var i = 1; // automatic variable
    final var i = 1; // final automatic variable
    return;
} // at this point, all automatic variables cease to exist
```

- due to 2's complement method of representing negative integers, the below method always returns -1
```java
int negativeOne(int input) {
    return input^~input;
}
```
- nested class is any class declared in another class/interface
  - inner class is any implicit or explicit static nested class
  - a class defined inside an interface is implicitly static
  - as of Java 16, inner classes are allowed to have static members
- an enum is either implicitly final if no constants with class body are defined, or implicitly sealed if it has at least one constant with body class defined 
```java
// implicit narrowing occurs only for int, char, byte and short, mind that this does not occur for long, float and double
short s = 127;
byte b = s; // does not compile

final short s = 127;
byte b = s; // this does compile

short s = 128;
byte b = s; // does not compile

final short s = 128;
byte b = s; // does not compile either

// albeit, implicit widening between long to float and long to double is possible  
long l = 10L;
double d = l;// this compiles just fine
float f = l;// this compiles just fine
```
- native methods cannot have a body
- Virtual threads are daemons and calling `setDaemon(false)` throws an `IllegalArgumentException` 
- A map object cannot act as a key on itself, (e.g. `var map = Map.of(...); map.put(map, ...);`)
- `Map#put(key, value)` returns the value of the key prior replacement (if existing, otherwise `null`)
- if element is not found by binarySearch, it returns the position (-(insertion point) - 1), ⚠️ beware that the arrays must preemptively be sorted
- records may have either one explicit canonical, one explicit compact constructor, or none 
- it's possible to make recursive calls of synchronized methods as they can reacquire the lock they already possess
- comparison operators (> == < have lower precedence over mathematical ones)
- anything != 0 number divided by 0.0f, 0.0 will return INFINITY, whereas 0 divided by any of the aforementioned will return NaN 
- Arrays.asList creates a list backed on the array, meaning that if the array changes, so that the List. It's important noting that add or remove are not allowed on the list, or else UnsupportedOperationException
- `Exception#toString` only prints the exception name + message (and not the stacktrace) 
- `if (false) {...}` does not generate a compile-time error, which is an exception to the rule for optimizations, however, `while(false) {...}` or `for(;false;) {...}` won't compile
- Java always passes parameters by value, and for objects, it passes the reference value that sometimes can lead us think that we're passing by reference when we change the state of the object referenced by the parameter passed into the method
- A virtual call is when a method call is bound at runtime and not at compile time, therefore, all non-private and non-final instance methods calls are virtual 
- Stack overflow only occurs in recursive calls
- It's not allowed to access static fields in enum constructors (not even effectively finals)

### Understanding Arrays#compare and Arrays#mismatch
It uses lexicographical comparison (i.e. dictionary-like) while comparing letter by letter until finding the first mismatch:

```java

import java.util.Arrays;

// Rule 1: when matching exact prefix returns the number of additional extra elements (regardless of their values)
char[] array1 = {'c', 'a', 'r'};
char[] array2 = {'c', 'a', 'r', 'w', 'a', 's', 'h'};

Arrays.compare(array1, array2); // -4
Arrays.compare(array2, array1); // -4

// Rule 2: when mismatching the first element found from left to right, returns the result of comparing that element 
char[] array3 = {'c', 'a', 'r', 'v'};
char[] array4 = {'c', 'a', 'r', 'w', 'a', 's', 'h'};
Arrays.compare(array3, array4); // -1, be it car u, the result would've been -2 because Character.compare('u', 'w') == -2
Arrays.compare(array4, array3); // 1

// Rule 3: when exactly the same, returns 0
Arrays.compare(array3, array3) == 0
```

As per the Arrays.mismatch, the rules are much simpler:
```java
import java.util.Arrays;

// Rule 1: returns the first index from left to right of the mismatching element (regardless of the parameter order sent when calling the mismatch method)
char[] array1 = {'a', 'b', 'c'};
char[] array2 = {'a', 'b', 'c', 'd', 'e'};
Arrays.mismatch(array1, array2) == Arrays.mismatch(array2, array1) == 3 

// Rule 2: returns 0 when none of the elements match
char[] array3 = {'1', '2', '3'};
char[] array4 = {'4', '5', '6', '7'};
char[] array5 = {'1', '2', '3'};
char[] array6 = {};
Arrays.mismatch(array3, array4) == Arrays.mismatch(array5, array6) == 0

// Rule 3: when matching comparison returns -1
char[] array7 = {'1', '2', '3'};
char[] array8 = {'1', '2', '3'};
Arrays.mismatch(array7, array8) == -1
```
