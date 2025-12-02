package com.javacorepractice.foundation.generic;

public class GenericClass<T> {
    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void printValue(T value) {
        setValue(value);
        System.out.println("T value: " + getValue());
    }
}
