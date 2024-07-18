package com.example.models;

import java.time.LocalDate;

public class Student extends User {
  private final LocalDate dob;
  private final Course course;

  public Student(String firstName, String lastName, String email, String userId, String password, Gender gender,
      Course course, LocalDate dob) {
    super(firstName, lastName, email, userId, password, gender, Role.STUDENT);
    this.course = course;
    this.dob = dob;
  }

  public LocalDate getDob() {
    return dob;
  }

  public Course getCourse() {
    return course;
  }
}
