package com.javacorepractice.solid;

/*
 Principle: Interface Segregation (ISP)
 -------------------------------------
 English:
 The Interface Segregation Principle states that interfaces should be small and focused.
 A class should never be forced to implement methods it doesn’t need.
 In this example, Dog implements only Walker, Fish implements only Swimmer,
 and Duck implements both because it can both walk and swim.
 The Trainer depends only on Walker — it doesn’t care about swimming.
 The presence of Swimmer shows that other behavior (like swimming) can exist
 independently and be used by other classes (for example, a SwimCoach).
 This separation keeps dependencies minimal and code flexible.
*/

public class I_InterfaceSegregation {

    interface Walker {
        String walk();
    }

    interface Swimmer {
        String swim();
    }

    static class Dog implements Walker {
        @Override
        public String walk() {
            return "Dog walks";
        }
    }

    static class Fish implements Swimmer {
        @Override
        public String swim() {
            return "Fish swims";
        }
    }

    static class Duck implements Walker, Swimmer {
        @Override
        public String walk() {
            return "Duck walks";
        }

        @Override
        public String swim() {
            return "Duck swims";
        }
    }

    static class Trainer {
        String doWalk(Walker w) {
            return w.walk();
        }
    }

    public static void main(String[] args) {
        Trainer t = new Trainer();
        System.out.println(t.doWalk(new Dog()));
        System.out.println(t.doWalk(new Duck()));
        // t.doWalk(new Fish()); // compile-time: Fish is not a Walker → correct separation
    }
}