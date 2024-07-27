package com.example.controllers;

import java.io.IOException;

import com.example.exceptions.BadRequestException;
import com.example.models.Gender;
import com.example.models.Teacher;
import com.example.models.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

public class TeachersCreationFormController extends DashboardController {

  @FXML
  private AnchorPane rootPane;

  @FXML
  private TextField firstNameField;

  @FXML
  private TextField lastNameField;

  @FXML
  private ComboBox<Gender> genderComboBox;

  @FXML
  private TextField emailField;

  @FXML
  private PasswordField passwordField;

  @FXML
  private PasswordField confirmPasswordField;

  UserDataController userDataController;

  public void initialize() {
    genderComboBox.getItems().setAll(Gender.values());
    this.userDataController = new UserDataController();
  }

  @FXML
  private void handleBack() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/teachers_table_view.fxml"));
      Parent dashboard = loader.load();
      TeachersTableViewController controller = loader.getController();
      controller.setUser(user);

      Scene scene = new Scene(dashboard);
      Stage stage = (Stage) rootPane.getScene().getWindow();

      stage.setScene(scene);
      stage.show();
    } catch (IOException err) {
      PopupController.showPopup("500-Internal Server Error", "Something Went Wrong");
    }
  }

  @FXML
  private void handleSubmit() {
    String firstName = firstNameField.getText();
    String lastName = lastNameField.getText();
    Gender gender = genderComboBox.getValue();
    String email = emailField.getText();
    String password = passwordField.getText();
    String confirmPassword = confirmPasswordField.getText();

    try {
      if (validateForm(firstName, lastName, gender, email, password, confirmPassword)) {
        User newUser = new Teacher(firstName, lastName, email, password, gender);
        userDataController.addUser(newUser);
        PopupController.showPopup("200-Sucess", "New User with User ID " + newUser.getId() + " created sucessfully.");
      }

    } catch (BadRequestException err) {
      PopupController.showPopup(err.getErrorTitle(), err.getMessage());
    }

  }

  private boolean validateForm(String firstName, String lastName, Gender gender, String email, String password,
      String confirmPassword) throws BadRequestException {

    if (firstName.trim() == null || firstName.trim().isEmpty()) {
      throw new BadRequestException("First Name cannot be empty.");
    }

    if (!firstName.matches("[A-Za-z]+")) {
      throw new BadRequestException("First Name cannot contain special characters or numbers.");
    }
    if (lastName.trim() == null || lastName.trim().isEmpty()) {
      throw new BadRequestException("Last Name cannot be empty.");
    }

    if (!lastName.matches("[A-Za-z]+")) {
      throw new BadRequestException("Last Name cannot contain special characters or numbers.");
    }

    if (gender == null) {
      throw new BadRequestException("Gender is required.");
    }

    if (email.trim() == null || email.trim().isEmpty()) {
      throw new BadRequestException("Email cannot be empty.");
    }
    if (!email.matches("^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+$")) {
      throw new BadRequestException("Please enter a valid email address.");
    }
    if (password == null) {
      throw new BadRequestException("Password is required.");
    }

    if (password.length() < 8) {
      throw new BadRequestException("Password must be at least 8 characters");
    }

    if (!password.equals(confirmPassword)) {
      throw new BadRequestException("Confirmation password doesnot match the password.");
    }

    return true;
  }

}
