package com.javacorepractice.solid;

/*
 Demonstrates Open/Closed Principle (OCP):
 A class or module should be open for extension but closed for modification.
 Here, we can extend functionality by adding new enum constants (e.g., JUMP)
 without changing existing code — TrickPerformer remains the same.
 This prevents breaking existing logic when expanding behavior.
*/
public class O_OpenClosed {

    enum Trick {
        SIT {
            @Override
            String perform(String name) {
                return name + " sits";
            }
        },
        VOICE {
            @Override
            String perform(String name) {
                return name + " barks";
            }
        };
        // Extend: add JUMP { @Override String perform(...) { ... } } — no changes in TrickPerformer.

        abstract String perform(String name);
    }

    /*
 TrickPerformer acts as a simple delegator — it calls the perform() method of Trick.
 It could be omitted in real code, but is kept here to demonstrate the Open/Closed principle:
 we can add new behavior (new Trick types) without touching this class.
*/
    static class TrickPerformer {
        String doTrick(String petName, Trick trick) {
            return trick.perform(petName);
        }
    }

    public static void main(String[] args) {
        TrickPerformer tp = new TrickPerformer();
        System.out.println(tp.doTrick("Buddy", Trick.SIT));
        System.out.println(tp.doTrick("Buddy", Trick.VOICE));
    }
}