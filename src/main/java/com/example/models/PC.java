package com.example.models;

import com.example.helpers.Utils;

public class PC extends User {

  public PC(String firstName, String lastName, String email, String password, Gender gender) {
    super(firstName, lastName, email, Utils.generateId(Role.ADMIN.toString()), password, gender, Role.PC);
  }

}
