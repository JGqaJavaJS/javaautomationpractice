package com.javacorepractice.oop;

public class User {
    private final int id;
    private String email;
    private String password;

    public User(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return "Generic User";
    }

    public void printId(){}

    @Override
    public String toString() {
        return "UserEncapsulation{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
