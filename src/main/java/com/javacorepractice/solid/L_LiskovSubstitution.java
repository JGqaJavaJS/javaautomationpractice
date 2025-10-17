package com.javacorepractice.solid;

/*
 Principle: Liskov Substitution (LSP)
 -------------------------------------
 English:
 Subclasses must be replaceable for their base class without altering the correctness of the program.
 In this example, Dog and Cat both extend Animal. The Announcer class works with the base type (Animal),
 and it doesn’t care which specific subclass is passed — both behave as expected.
 Each subclass refines behavior (overrides methods) but never breaks the parent’s contract.
*/

public class L_LiskovSubstitution {

    static abstract class Animal {

        abstract String voice();
        // must return a sound

        String label() {
            return "Animal";
        }
        // common behavior
    }

    static class Dog extends Animal {
        @Override
        String voice() {
            return "Woof";
        }

        @Override
        String label() {
            return "Dog";
        }
        // refines, doesn't break expectations
    }

    static class Cat extends Animal {
        @Override
        String voice() {
            return "Meow";
        }
        // label() inherited as "Animal"
    }

    static class SilentFish extends Animal {
        @Override
        String voice() {
            throw new UnsupportedOperationException("Fish can’t talk!");
        }
    }

    static class Announcer {
        String announce(Animal a) {
            return String.format("%s says: %s", a.label(), a.voice());
        }
    }

    public static void main(String[] args) {
        Announcer an = new Announcer();
        System.out.println(an.announce(new Dog())); // Dog says: Woof
        System.out.println(an.announce(new Cat())); // Animal says: Meow
        System.out.println(an.announce(new SilentFish())); // violates LSP
    }
}
