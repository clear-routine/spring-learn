package com.tt;

/**
 * c-namespace 演示：构造器注入
 */
@SuppressWarnings("all")
public class User {

    private final User manager;
    private final String email;

    public User(User manager, String email) {
        this.manager = manager;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{manager=" + (manager != null ? "User" : "null") + ", email='" + email + "'}";
    }
}
