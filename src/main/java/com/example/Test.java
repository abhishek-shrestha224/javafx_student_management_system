package com.example;

import java.time.LocalDate;

import com.example.controllers.UserDataController;
import com.example.models.Admin;
import com.example.models.Course;
import com.example.models.Gender;
import com.example.models.Student;
import com.example.models.Teacher;
import com.example.models.User;

public class Test {

  public static void test() {
    // Initialize UserController
    UserDataController userController = new UserDataController();

    // Create a Course object
    Course csCourse = new Course("Computer Science", 4, 3);

    // Create and add a Student
    User student = new Student("John", "Doe", "student  email", "password123", Gender.MALE, csCourse,
        LocalDate.of(2000, 1, 1));
    userController.addUser(student);
    System.out.println("Added Student: " + student.getFirstName() + " " + student.getLastName());

    // Create and add a Teacher
    User teacher = new Teacher("Jane", "Smith", "jane.smith@example.com", "password123", Gender.FEMALE, csCourse);
    userController.addUser(teacher);
    System.out.println("Added Teacher: " + teacher.getFirstName() + " " + teacher.getLastName());

    // Create and add an Admin
    User admin = new Admin();
    userController.addUser(admin);
    System.out.println("Added Admin: " + admin.getFirstName() + " " + admin.getLastName());

    // Retrieve and display a user
    User retrievedStudent = userController.getUser(student.getUserId());
    System.out.println("Retrieved Student Email: " + retrievedStudent);

    // Update user details
    student.setFirstName("Johnathan");
    userController.updateUser(student);
    System.out.println("Updated Student First Name: " + userController.getUser(student.getUserId()));

    // Delete a user
    userController.deleteUser(teacher.getUserId());
    User deletedTeacher = userController.getUser(teacher.getUserId());
    if (deletedTeacher == null) {
      System.out.println("Teacher successfully deleted.");
    } else {
      System.out.println("Teacher was not deleted.");
    }
  }
}
