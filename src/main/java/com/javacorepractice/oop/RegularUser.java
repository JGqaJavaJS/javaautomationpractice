package com.javacorepractice.oop;

public class RegularUser extends User {


    public RegularUser(int id, String email, String password) {
        super(id, email, password);
    }

    @Override
    public String getRole() {
        return "Regular User";
    }
}
