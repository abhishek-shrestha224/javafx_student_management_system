package com.example.models;

import com.example.helpers.Utils;

public class Admin extends User {
  public Admin() {
    super("Admin", "User", "admin@iimscollege.edu.np", Utils.generateId(),
        "password",
        Gender.MALE, Role.ADMIN);
  }

}
