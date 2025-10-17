package com.javacorepractice.solid;

/*
 Principle: Dependency Inversion (DIP)
 -------------------------------------
 English:
 The Dependency Inversion Principle says that high-level modules should depend on abstractions,
 not on concrete implementations.
 In this example, VetClinic (the high-level module) depends on the Notifier interface,
 not on a specific class like EmailNotifier.
 This allows swapping implementations (e.g., SmsNotifier, PushNotifier) without changing VetClinic’s code.
 It keeps the system flexible, extensible, and easy to test.
*/

public class D_DependencyInversion {

    // Abstraction — VetClinic depends on this, not on concrete implementations
    interface Notifier {
        void notifyOwner(String msg);
    }

    /* Concrete notifier #1 — can be swapped at runtime */
    private static class EmailNotifier implements Notifier {
        @Override
        public void notifyOwner(String msg) {
            System.out.println("[Email] " + msg);
        }
    }

    /* Concrete notifier #2 — interchangeable with EmailNotifier */
    private static class SmsNotifier implements Notifier {
        @Override
        public void notifyOwner(String msg) {
            System.out.println("[SMS] " + msg);
        }
    }

    /* High-level module — depends on abstraction (Notifier), not on concrete types */
    static class VetClinic {
        private final Notifier notifier;

        VetClinic(Notifier notifier) {
            this.notifier = notifier;
        }

        String vaccinate(String petName) {
            String res = petName + " vaccinated";
            notifier.notifyOwner(res);
            return res;
        }
    }

    public static void main(String[] args) {
        VetClinic emailClinic = new VetClinic(new EmailNotifier());
        VetClinic smsClinic = new VetClinic(new SmsNotifier());

        System.out.println(emailClinic.vaccinate("Mittens"));
        System.out.println(smsClinic.vaccinate("Buddy"));
    }
}