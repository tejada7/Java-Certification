package com.javacertification.interviewquestions;

import com.javacertification.datastructures.Stack;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.function.Function;

/**
 * <p>
 * The reverse Polish notation (RPN) is a different way of writing arithmetic expressions. In the RPN, the operand is
 * placed after the number, e.g. 2 2 +. It seems strange for many people, but such expressions allow you to abandon the
 * use of parentheses in expressions, as RPN unambiguously determines the order of actions performed.
 * </p>
 * <p>
 * This class implements a generic functionality of this notation taking into account the 4 basic arithmetic operations.
 * The best way to implement this is by using a stack.
 * </p>
 *
 * @param <E> the generic type
 * @see Stack
 */
public class ReversePolishNotation<E extends Number> implements Function<String, E> {

    private static final String OPERATORS = "+-*/";

    private static final String INTEGER = "Integer";
    private static final String FLOAT = "Float";
    private static final String LONG = "Long";
    private static final String DOUBLE = "Double";
    private static final String SHORT = "Short";
    private static final String BYTE = "Byte";
    private static final String BIG_DECIMAL = "BigDecimal";
    private static final String BIG_INTEGER = "BigInteger";

    private Class<E> typeParameterClass;
    private Stack<String> stack;

    /**
     * Takes a {@link Class} as a parameter in order to identify the type object of the generic at runtime.
     *
     * @param typeParameterClass the class type of the generic
     */
    public ReversePolishNotation(Class<E> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }

    @Override
    public E apply(String expression) {
        return evaluate(expression);
    }

    @SuppressWarnings("unchecked")
    private E evaluate(String expression) {
        String[] tokens = expression.split(StringUtils.SINGLE_SPACE);
        stack = new Stack<>();
        for (String token : tokens) {
            if (OPERATORS.contains(token)) {
                Number b = getValue();
                Number a = getValue();
                switch (token) {
                    case "+":
                        stack.push(String.valueOf(addNumbers(a, b)));
                        break;
                    case "-":
                        stack.push(String.valueOf(subtractNumbers(a, b)));
                        break;
                    case "*":
                        stack.push(String.valueOf(multiplyNumbers(a, b)));
                        break;
                    case "/":
                        stack.push(String.valueOf(divideNumbers(a, b)));
                        break;
                    default:
                        throw new UnsupportedOperationException(String.format("%s operation not implemented", token));
                }
            } else {
                stack.push(token);
            }
        }
        return (E) getValue();
    }

    private Number divideNumbers(Number a, Number b) {
        switch (typeParameterClass.getSimpleName()) {
            case INTEGER:
                return a.intValue() / b.intValue();
            case FLOAT:
                return a.floatValue() / b.floatValue();
            case LONG:
                return a.longValue() / b.longValue();
            case DOUBLE:
                return a.doubleValue() / b.doubleValue();
            case SHORT:
                return a.shortValue() / b.shortValue();
            case BYTE:
                return a.byteValue() / b.byteValue();
            case BIG_DECIMAL:
                return new BigDecimal(a.toString()).divide(new BigDecimal(b.toString()));
            case BIG_INTEGER:
                return new BigInteger(a.toString()).divide(new BigInteger(b.toString()));
            default:
                return null;
        }
    }

    private Number multiplyNumbers(Number a, Number b) {
        switch (typeParameterClass.getSimpleName()) {
            case INTEGER:
                return a.intValue() * b.intValue();
            case FLOAT:
                return a.floatValue() * b.floatValue();
            case LONG:
                return a.longValue() * b.longValue();
            case DOUBLE:
                return a.doubleValue() * b.doubleValue();
            case SHORT:
                return a.shortValue() * b.shortValue();
            case BYTE:
                return a.byteValue() * b.byteValue();
            case BIG_DECIMAL:
                return new BigDecimal(a.toString()).multiply(new BigDecimal(b.toString()));
            case BIG_INTEGER:
                return new BigInteger(a.toString()).multiply(new BigInteger(b.toString()));
            default:
                return null;
        }
    }

    private Number subtractNumbers(Number a, Number b) {
        switch (typeParameterClass.getSimpleName()) {
            case INTEGER:
                return a.intValue() - b.intValue();
            case FLOAT:
                return a.floatValue() - b.floatValue();
            case LONG:
                return a.longValue() - b.longValue();
            case DOUBLE:
                return a.doubleValue() - b.doubleValue();
            case SHORT:
                return a.shortValue() - b.shortValue();
            case BYTE:
                return a.byteValue() - b.byteValue();
            case BIG_DECIMAL:
                return new BigDecimal(a.toString()).subtract(new BigDecimal(b.toString()));
            case BIG_INTEGER:
                return new BigInteger(a.toString()).subtract(new BigInteger(b.toString()));
            default:
                return null;
        }
    }

    private Number addNumbers(Number a, Number b) {
        switch (typeParameterClass.getSimpleName()) {
            case INTEGER:
                return a.intValue() + b.intValue();
            case FLOAT:
                return a.floatValue() + b.floatValue();
            case LONG:
                return a.longValue() + b.longValue();
            case DOUBLE:
                return a.doubleValue() + b.doubleValue();
            case SHORT:
                return a.shortValue() + b.shortValue();
            case BYTE:
                return a.byteValue() + b.byteValue();
            case BIG_DECIMAL:
                return new BigDecimal(a.toString()).add(new BigDecimal(b.toString()));
            case BIG_INTEGER:
                return new BigInteger(a.toString()).add(new BigInteger(b.toString()));
            default:
                return 0;
        }
    }

    private Number getValue() {
        switch (typeParameterClass.getSimpleName()) {
            case INTEGER:
                return Integer.parseInt(stack.pop());
            case FLOAT:
                return Float.parseFloat(stack.pop());
            case LONG:
                return Long.parseLong(stack.pop());
            case DOUBLE:
                return Double.parseDouble(stack.pop());
            case SHORT:
                return Short.parseShort(stack.pop());
            case BYTE:
                return Byte.parseByte(stack.pop());
            case BIG_DECIMAL:
                return new BigDecimal(stack.pop());
            case BIG_INTEGER:
                return new BigInteger(stack.pop());
            default:
                return 0;
        }
    }
}
