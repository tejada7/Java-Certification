package com.javacertification.functionalprogramming;

import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class VavrValidator {

    public record Foo(String name, int age, LocalDate birthDate) { }

    public static class FooValidator {

        public static Validation<Seq<String>, Foo> validateFoo(final String name, final int age,
                                                               final LocalDate birthDate) {
            return Validation.combine(validateName(name), validateAge(age), validateBirthDate(birthDate))
                    .ap(Foo::new);
        }

        private static Validation<String, LocalDate> validateBirthDate(final LocalDate birthDate) {
            return birthDate != null && birthDate.isBefore(LocalDate.of(2050, 1, 1))
                    ? Validation.valid(birthDate)
                    : Validation.invalid("Incoherent birth date");
        }

        private static Validation<String, Integer> validateAge(final int age) {
            return age > 0 && age < 120
                    ? Validation.valid(age)
                    : Validation.invalid("the age must be greater than 0 and less than 120");
        }

        private static Validation<String, String> validateName(final String name) {
            return Validation.combine(
                            StringUtils.isNotBlank(name)
                                    ? Validation.valid(name)
                                    : Validation.invalid("name cannot be empty"),
                            StringUtils.defaultIfBlank(name, EMPTY).length() < 30
                                    ? Validation.valid(name)
                                    : Validation.invalid("name cannot exceed the 120 characters"))
                    .ap((name1, name2) -> name1)
                    .mapError(errors -> errors.collect(Collectors.joining(",")));
        }
    }
}
