package com.example.models;

import java.time.LocalDate;

import com.example.helpers.Utils;

public class Student extends User {
  private final LocalDate dob;
  private final Course course;

  public Student(String firstName, String lastName, String email, String password, Gender gender,
      Course course, LocalDate dob) {
    super(firstName, lastName, email, Utils.generateId(Role.STUDENT.toString()), password, gender, Role.STUDENT);
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
