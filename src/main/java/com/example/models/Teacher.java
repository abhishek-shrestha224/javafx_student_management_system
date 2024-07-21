package com.example.models;

import com.example.helpers.Utils;

public class Teacher extends User {

  public Teacher(String firstName, String lastName, String email, String password, Gender gender) {
    super(firstName, lastName, email, Utils.generateId(), password, gender, Role.TEACHER);

  }

}
