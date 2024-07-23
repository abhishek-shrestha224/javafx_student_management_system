package com.example.models;

import com.example.helpers.Utils;

public class Evaluation {
  private final int id, studentId;
  private final String feedback, studentFullName;

  public Evaluation(String feedback, User student) {
    this.id = Utils.generateId();
    this.feedback = feedback;
    this.studentId = student.getId();
    this.studentFullName = student.getFirstName() + " " + student.getLastName();
  }

  // Getters and setters
  public int getId() {
    return id;
  }

  public int getStudentId() {
    return studentId;
  }

  public String getFeedback() {
    return feedback;
  }

  public String getStudentFullName() {
    return studentFullName;
  }

}
