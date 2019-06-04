package com.oca.design_patterns.creational.builder;

import java.time.LocalDate;

/**
 * Created by Favio on 11/11/2017.
 */
public class IdentityBean {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    public IdentityBean(IdentityBeanBuilder builder) {
        this.setId(builder.id);
        this.setFirstName(builder.firstName);
        this.setLastName(builder.lastName);
        this.setBirthDate(builder.birthDate);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "IdentityBean{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

    static class IdentityBeanBuilder {
        private int id;
        private String firstName;
        private String lastName;
        private LocalDate birthDate;

        public IdentityBeanBuilder() {
        }

        public IdentityBeanBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public IdentityBeanBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public IdentityBeanBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public IdentityBeanBuilder withBirthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public IdentityBean build() {
            return new IdentityBean(this);
        }
    }
}
