package org.example.prototype;


import java.util.HashMap;
import java.util.Map;

public class UserPrototypeExample {

    public static void main(String[] args) {
        PrototypeRegistry registry = new PrototypeRegistry();

        UserPrototype userPrototype1 = new UserPrototype("User 1");
        UserPrototype userPrototype2 = new UserPrototype("User 2");

        registry.addPrototype("Prototype1", userPrototype1);
        registry.addPrototype("Prototype2", userPrototype2);

        Prototype clonedPrototype1 = registry.getPrototype("Prototype1");
        Prototype clonedPrototype2 = registry.getPrototype("Prototype2");

        System.out.println("Cloned Prototype 1: " + clonedPrototype1.makeClone() + " " + clonedPrototype1.name());
        System.out.println("Cloned Prototype 2: " + clonedPrototype2.makeClone() + " " + clonedPrototype2.name());
    }

    private static class PrototypeRegistry {
        private final Map<String, Prototype> registry = new HashMap<>();

        public void addPrototype(String key, Prototype prototype) {
            registry.put(key, prototype);
        }

        public Prototype getPrototype(String key) {
            return registry.get(key);
        }

    }

    public interface Prototype {
        Prototype makeClone();

        default String name() {
            return "";
        }
    }


    public record UserPrototype(String name) implements Prototype {

        @Override
        public Prototype makeClone() {
            return new UserPrototype(this.name);
        }
    }
}
