package com.example;

import java.io.IOException;

import com.example.exceptions.NotFoundException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

  @Override
  public void start(Stage stage) throws IOException, NotFoundException {
    // UserDataController controller = new UserDataController();
    // Map<Integer, User> users = controller.getAllUsers();

    // User teacher = new Teacher("Submit", "Timalsina", "teacher@email.com",
    // "password", Gender.MALE);
    // User admin = new Admin();
    // User student = new Student("Abhishek", "Shrestha", "student@email.com",
    // "password", Gender.MALE);

    // controller.addUser(teacher);
    // controller.addUser(admin);
    // controller.addUser(student);

    // for (Map.Entry<Integer, User> entry : users.entrySet()) {
    // Integer userId = entry.getKey();
    // User user = entry.getValue();

    // // Display user information (assuming User class has a toString method)
    // System.out.println("User ID: " + userId + ", First Name " +
    // user.getFirstName());
    // }

    Parent root = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
    Scene scene = new Scene(root);
    stage.setScene(scene);

    stage.show();

  }

  public static void main(String[] args) {
    launch();
  }

}