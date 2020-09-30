package com.javacertification.miscellaneous;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class LinkedEnumTest {


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
        Assert.assertEquals(Car.TOYOTA, nextCar);
    }

    @Test
    void shouldGetPreviousConstant() {
        // Given
        final Car car = Car.NISSAN;

        // When
        final Car previousCar = car.previous();

        // Then
        Assert.assertEquals(Car.CHEVI, previousCar);
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
        Assert.assertNull(unknownCar1);
        Assert.assertNull(unknownCar2);
    }
}
