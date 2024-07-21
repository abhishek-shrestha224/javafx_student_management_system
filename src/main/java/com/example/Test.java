package com.example;

import com.example.controllers.UserDataController;
import com.example.models.Admin;
import com.example.models.Gender;
import com.example.models.Student;
import com.example.models.Teacher;
import com.example.models.User;

public class Test {
  public static void test() {
    UserDataController userDataController = new UserDataController();
    User student1 = new Student("Abhishek", "Shrestha", "abhi@iimscollege.edu.np", "password", Gender.MALE);
    User student2 = new Student("Sanjib", "Dhital", "sanjib@iimscollege.edu.np", "secretkey", Gender.MALE);
    User student3 = new Student("Pranisha", "Maharjan", "paranisha@iimscollege.edu.np", "pass1234", Gender.FEMALE);
    User teacher = new Teacher("Subit", "Timalsina", "subit@iimscollege.edu.np", "teacher123", Gender.MALE);
    User admin = new Admin();
    userDataController.addUser(student1);
    userDataController.addUser(student2);
    userDataController.addUser(student3);
    userDataController.addUser(teacher);
    userDataController.addUser(admin);
    System.out.println(student1.getId() + student1.getFirstName());
    System.out.println(student2.getId() + student2.getFirstName());
    System.out.println(student3.getId() + student3.getFirstName());
    System.out.println(teacher.getId() + teacher.getFirstName());
    System.out.println(admin.getId() + admin.getFirstName());
  }

}
