package com.javacorepractice.oop;

public class AdminUser extends User {
    public AdminUser(int id, String email, String password)
    {
        super(id, email, password);
    }

    @Override
    public String getRole() {
        return "Administrator";
    }

    public void printEmail() {
        System.out.println("Admin email: " + getEmail());
    }

    @Override
    public void printId() {
        System.out.println("Admin ID: " + getId());
    }
}
