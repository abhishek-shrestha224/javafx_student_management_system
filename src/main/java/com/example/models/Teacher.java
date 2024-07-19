package com.example.models;

import com.example.helpers.Utils;

public class Teacher extends User {
  private final Course course;

  public Teacher(String firstName, String lastName, String email, String password, Gender gender,
      Course course) {
    super(firstName, lastName, email, Utils.generateId(Role.TEACHER.toString()), password, gender, Role.TEACHER);
    this.course = course;
  }

  public Course getCourse() {
    return course;
  }

}
