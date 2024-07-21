package com.example;

import java.time.LocalDate;

import com.example.controllers.UserDataController;
import com.example.exceptions.NotFoundException;
import com.example.models.Admin;
import com.example.models.Course;
import com.example.models.Gender;
import com.example.models.Student;
import com.example.models.Teacher;

public class Test {

  public static void test() throws NotFoundException {
    // Initialize UserController
    UserDataController userController = new UserDataController();

    // Create a Course object
    Course csCourse = new Course("Computer Science", 4, 3);

    // Create and add a Student
    Student student = new Student("John", "Doe", "student  email", "password123", Gender.MALE, csCourse,
        LocalDate.of(2000, 1, 1));
    userController.addUser(student);

    // Create and add a Teacher
    Teacher teacher = new Teacher("Jane", "Smith", "jane.smith@example.com", "password123", Gender.FEMALE, csCourse);
    userController.addUser(teacher);

    // Create and add an Admin
    Admin admin = new Admin();
    userController.addUser(admin);

    // Retrieve and display a user

    // Update user details
    student.setFirstName("Johnathan");
    userController.updateUser(student);

  }
}
