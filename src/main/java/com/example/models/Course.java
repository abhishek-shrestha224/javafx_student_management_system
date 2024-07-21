package com.example.models;

import com.example.helpers.Utils;

public class Course {
  private final String courseName, courseId;
  private final int duration, creditHours;

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



  public String getCourseName() {
    return courseName;
  }


  public int getDuration() {
    return duration;
  }



  public int getCreditHours() {
    return creditHours;
  }



  @Override
  public String toString() {
    return courseName;
  }
}
