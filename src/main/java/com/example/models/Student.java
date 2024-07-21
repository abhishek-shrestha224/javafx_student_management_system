package com.example.models;

import com.example.helpers.Utils;

public class Student extends User {

  public Student(String firstName, String lastName, String email, String password, Gender gender) {
    super(firstName, lastName, email, Utils.generateId(), password, gender, Role.STUDENT);

  }

}
