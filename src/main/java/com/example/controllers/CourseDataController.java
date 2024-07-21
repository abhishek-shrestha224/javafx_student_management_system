package com.example.controllers;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.helpers.PATH;
import com.example.models.Course;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CourseDataController {
  private final Map<String, Course> courses = new HashMap<>();
  private final Gson gson;

  public CourseDataController() {
    gson = new Gson();
    loadCourses();
  }

  // Create
  public void addCourse(Course course) {
    courses.put(course.getCourseId(), course);
    saveCourses();
  }

  // Read
  public Course getCourseById(String courseId) {
    return courses.get(courseId);
  }

  public List<Course> getAllCourses() {
    return courses.values().stream().collect(Collectors.toList());
  }

  // Delete
  public void deleteCourse(String courseId) {
    if (courses.containsKey(courseId)) {
      courses.remove(courseId);
      saveCourses();
    }
  }

  // Load courses from JSON file
  private void loadCourses() {
    try (Reader reader = new FileReader(PATH.COURSE.getFilePath())) {
      Type courseMapType = new TypeToken<Map<String, Course>>() {
      }.getType();
      Map<String, Course> loadedCourses = gson.fromJson(reader, courseMapType);
      if (loadedCourses != null) {
        courses.putAll(loadedCourses);
      }
    } catch (IOException e) {
    }
  }

  // Save courses to JSON file
  private void saveCourses() {
    try (Writer writer = new FileWriter(PATH.COURSE.getFilePath())) {
      gson.toJson(courses, writer);
    } catch (IOException e) {
    }
  }
}
