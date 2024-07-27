package com.example.controllers;

import java.io.IOException;

import com.example.exceptions.BadRequestException;
import com.example.exceptions.NotFoundException;
import com.example.helpers.Utils;
import com.example.models.Gender;
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

public class TeachersEditFormController extends DashboardController {

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

  private User workingUser;

  public void initialize() {
    genderComboBox.getItems().setAll(Gender.values());
    this.userDataController = new UserDataController();
  }

  public void fillForm(User workingUser) {
    firstNameField.setText(workingUser.getFirstName());
    lastNameField.setText(workingUser.getLastName());
    emailField.setText(workingUser.getEmail());
    genderComboBox.setValue(workingUser.getGender());
    this.workingUser = workingUser;
  }

  private void editUser(String firstName, String lastName, Gender gender, String email,
      String password) {
    if (!this.workingUser.getFirstName().equals(firstName)) {
      this.workingUser.setFirstName(firstName);
    }
    if (!this.workingUser.getLastName().equals(lastName)) {
      this.workingUser.setLastName(lastName);
    }
    if (!this.workingUser.getEmail().equals(email)) {
      this.workingUser.setEmail(email);
    }
    if (this.workingUser.getGender() != gender) {
      this.workingUser.setGender(gender);
    }

    // Check if the password is provided and is different from the current password
    if (password != null && !password.isEmpty()) {
      String hashedPassword = Utils.getSha256Hash(password);
      // Update password only if it's different from the existing password hash
      if (!hashedPassword.equals(this.workingUser.getPassword()) && !password.equals(this.workingUser.getPassword())) {
        this.workingUser.setPassword(hashedPassword);
      }
    }
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
    try {
      String firstName = firstNameField.getText();
      String lastName = lastNameField.getText();
      Gender gender = genderComboBox.getValue();
      String email = emailField.getText();
      String password = passwordField.getText();
      String confirmPassword = confirmPasswordField.getText();

      if (validateForm(firstName, lastName, gender, email, password, confirmPassword)) {
        editUser(firstName, lastName, gender, email, password);
        userDataController.updateUser(workingUser);

        PopupController.showPopup("200-Sucess",
            "New User with User ID " + this.workingUser.getId() + " updated sucessfully.");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/teachers_table_view.fxml"));
        Parent dashboard = loader.load();
        TeachersTableViewController controller = loader.getController();
        controller.setUser(user);

        Scene scene = new Scene(dashboard);
        Stage stage = (Stage) rootPane.getScene().getWindow();

        stage.setScene(scene);
        stage.show();
      }
    } catch (BadRequestException err) {
      PopupController.showPopup(err.getErrorTitle(), err.getMessage());
    } catch (NotFoundException err) {
      PopupController.showPopup(err.getErrorTitle(), err.getMessage());
    } catch (IOException err) {
      PopupController.showPopup("500-Internal Server Error", err.getMessage());
    }

  }

  private boolean validateForm(String firstName, String lastName, Gender gender, String email,
      String password, String confirmPassword) throws BadRequestException {

    // Validate first name
    if (firstName.trim().isEmpty()) {
      throw new BadRequestException("First Name cannot be empty.");
    }
    if (!firstName.matches("[A-Za-z]+")) {
      throw new BadRequestException("First Name cannot contain special characters or numbers.");
    }

    // Validate last name
    if (lastName.trim().isEmpty()) {
      throw new BadRequestException("Last Name cannot be empty.");
    }
    if (!lastName.matches("[A-Za-z]+")) {
      throw new BadRequestException("Last Name cannot contain special characters or numbers.");
    }

    // Validate gender
    if (gender == null) {
      throw new BadRequestException("Gender is required.");
    }

    // Validate email
    if (email.trim().isEmpty()) {
      throw new BadRequestException("Email cannot be empty.");
    }
    if (!email.matches("^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+$")) {
      throw new BadRequestException("Please enter a valid email address.");
    }

    // Validate password and confirm password
    if (password != null && !password.isEmpty() || confirmPassword != null && !confirmPassword.isEmpty()) {
      if (password.length() < 8) {
        throw new BadRequestException("Password must be at least 8 characters long.");
      }
      if (this.workingUser != null && (this.workingUser.getPassword().equals(Utils.getSha256Hash(password))
          || this.workingUser.getPassword().equals(password))) {
        throw new BadRequestException("New Password cannot be your old password.");
      }
      if (!password.equals(confirmPassword)) {
        throw new BadRequestException("Confirmation password does not match the password.");
      }
    }

    return true;
  }

}
