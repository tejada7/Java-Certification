package com.javacertification.miscellaneous;

/**
 * Allow an enum to get its next or previous constant.
 *
 * @param <E> the generic type
 */
public interface LinkedEnum<E extends Enum<E>> {

    /**
     * Polyphormic call from {@link Enum#ordinal()}
     *
     * @return the current index of the constant
     */
    int ordinal();

    /**
     * Get the next constant.
     *
     * @return a {@link E} constant if exist, otherwise null
     */
    default E next() {
        E[] elements = getAllValues();
        return (ordinal() != elements.length - 1) ? elements[ordinal() + 1] : null;
    }

    /**
     * Get the previous constant.
     *
     * @return a {@link E} constant if exist, otherwise null
     */
    default E previous() {
        E[] elements = getAllValues();
        return (ordinal() != 0) ? elements[ordinal() - 1] : null;
    }

    /**
     * Get all values from the enum by using reflection.
     *
     * @return an array of {@link E} objects
     */
    @SuppressWarnings("unchecked")
    default E[] getAllValues() {
        LinkedEnum[] elements = getClass().getEnumConstants();
        return (E[]) elements;
    }
}
