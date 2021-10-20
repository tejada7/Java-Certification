# Exercises extracted from Java Puzzles

#### 1. Oddity: The most effective implementation to determine if an int number is odd is the following:
```java
boolean isOdd(int num) {
	return (num & 1) != 0; // or (num % 2) != 0;
}
```
Because `(num % 2) == 1` only applies to `num > 0`, discarding negative numbers.

#### 2. Floating points
Binary floating-point is particularly ill-suited to monetary calculations, as it is impossible to represent 0.1—or any other negative power of 10—exactly as a finite-length binary fraction 
such as 1.1 and 2.1.  

Therefore, never ever use `BigDecimal(double)` constructor as `new BigDecimal(.1)` would lead to `0.1000000000000000055511151231257827021181583404541015625` 
Also, avoid `float` and `double` where exact answers are required; for monetary calculations, use `int`, `long`, `BigDecimal` or `BigMoney` 

#### 3. Long division
The promotion from int to long is a widening primitive conversion, which preserves the (incorrect) numerical value 
When working with large numbers, watch out for overflow—it’s a silent killer. 
 
#### 4. Multicast sout((int) (char) (byte) -1) -> ???

byte: `1111 1111`

char: `1111 1111 1111 1111` ~ 65535 widening and narrowing primitive conversion, The byte is converted to an int and the int to a char

int: `0000 0000 0000 0000 1111 1111 1111 1111`

If you are converting from a char value c to a wider type, and you don’t want sign extension, consider using a bit mask for clarity, even though it isn’t required:
```java
int i = c & 0xffff;
```

#### 5. Ternary operator corner cases
The ternary operator has itself a type which is determined by its operands which should be compatible, there are though 
special cases in the event numeric primitives are involved, for more details refer to [Dos equis problem](src/main/java/com/oca/puzzles/DosEquis.java) 
#### 6. Beware of the compound assignment operators (+=, -=, *=, /=, %=, <<=, >>=, >>>=, &=, ^=, and |=.)
There is always a cast to the left-hand side of the operation, e.g.
```java
int x = 1;
short y = 1;
x+=y; // x will become 2
y+=x; // Beware!!! int will be casted to short and we'd silently lose precision
y = y + x; // does not compile due to loss of precision
```
#### 7. Decimal literals are all positive
To write a decimal negative constant, we use the minus sign (-) and this rule does not apply to octacl and hexadecimal
numbers, whose high-order bit determines whether they're negative or not. For instance, 0xcafebabe is negative as its
first high-order bit is equals to 1.