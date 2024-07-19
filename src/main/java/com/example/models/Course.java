package com.example.models;

import com.example.helpers.Utils;

public class Course {
  private String courseName, courseId;
  private int duration, creditHours;

  // Constructor
  public Course(String courseName, int duration, int creditHours) {
    this.courseId = Utils.generateId("CRS");
    this.courseName = courseName;
    this.duration = duration;
    this.creditHours = creditHours;

  }

  // Getters and setters
  public String getCourseId() {
    return courseId;
  }

  public void setCourseID(String courseId) {
    this.courseId = courseId;
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public int getCreditHours() {
    return creditHours;
  }

  public void setCreditHours(int creditHours) {
    this.creditHours = creditHours;
  }

  @Override
  public String toString() {
    return courseName;
  }
}
