package com.javacertification.design_patterns.behavioral.memento;

/**
 * Advantages:
 * Externalize an object state usually to provide rollback or undo functionality
 * Shields complex internals from others
 * Recreates state
 *
 * Pitfalls:
 * Violate encapsulation
 * Can be expensive
 * Consider deleting history
 */
public class MementoDemo {

    public static void main(String[] args) {
        Caretaker caretaker = new Caretaker();

        Employee emp = new Employee();

        emp.setName("Favio Tejada");
        emp.setAddress("4 Av. Clemenceau");
        emp.setPhone("33-0771664493");

        System.out.println("Employee before save: " + emp);

        caretaker.save(emp);

        emp.setPhone("591-2-420954");

        caretaker.save(emp);

        System.out.println("Employee after changed phone number: " + emp);

        emp.setPhone("333-666-999"); // We have not called save!

        caretaker.revert(emp);

        System.out.println("Reverts to last save point: " + emp);

        caretaker.revert(emp);

        System.out.println("Reverted to original: " + emp);
    }
}
