package com.example.globemotors.models;

public class User {
    private String name;
    private String username;
    private String password;
    private String address;
    private String contact;
    private String email;

    public User(String name, String username, String password, String address, String contact, String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.address = address;
        this.contact = contact;
        this.email = email;
    }

}
