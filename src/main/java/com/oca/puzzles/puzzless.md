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
 
