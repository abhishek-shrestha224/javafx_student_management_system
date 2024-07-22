package com.example.models;

public class Admin extends User {
  public Admin() {
    super("Admin", "User", "admin@iimscollege.edu.np", 111,
        "password",
        Gender.MALE, Role.ADMIN);
  }

}
