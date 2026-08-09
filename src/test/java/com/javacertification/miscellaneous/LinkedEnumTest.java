package com.javacertification.miscellaneous;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LinkedEnumTest {

    enum Car implements LinkedEnum<Car> {
        ISUZU, TOYOTA, CHEVI, NISSAN;
    }

    @Test
    void shouldGetNextConstant() {
        // Given
        final Car car = Car.ISUZU;

        // When
        final Car nextCar = car.next();

        // Then
        assertEquals(Car.TOYOTA, nextCar);
    }

    @Test
    void shouldGetPreviousConstant() {
        // Given
        final Car car = Car.NISSAN;

        // When
        final Car previousCar = car.previous();

        // Then
        assertEquals(Car.CHEVI, previousCar);
    }

    @Test
    void shouldGetNull_WhenEdgeCases() {
        // Given
        final Car car1 = Car.NISSAN;
        final Car car2 = Car.ISUZU;

        // When
        final Car unknownCar1 = car1.next();
        final Car unknownCar2 = car2.previous();

        // Then
        then(unknownCar1).isNull();
        then(unknownCar2).isNull();
    }
}
