package com.javacertification.puzzles;

/**
 * The program strangely enough prints out 'X88', to understand what is going on, there are three rules to keep in mind
 * to analise this ternary-operation corner case:
 *
 * 1. If the second and third operands have the same type, that is the type of the conditional expression. In other
 * words, you can avoid the whole mess by steering clear of mixed-type computation.
 * 2. If one of the operands is of type T where T is byte, short, or char and the other operand is a constant expression
 * of type int whose value is representable in type T, the type of the conditional expression is T.
 * 3. Otherwise, binary numeric promotion is applied to the operand types, and the type of the conditional expression is
 * the promoted type of the second and third operands.
 */
public class DosEquis {

    public static void main(String...args) {
        char x = 'X';
        int i = 0;
        System.out.print(true ? x : 0);
        System.out.print(false ? i : x);
    }
}
