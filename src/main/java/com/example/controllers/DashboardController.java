package com.example.controllers;

import java.io.IOException;

import com.example.models.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DashboardController extends Controller {
  @FXML
  private Label fullName;

  @FXML
  private Label userId;

  @FXML
  private Label email;

  @FXML
  private Label role;

  @FXML
  private Label gender;

  @FXML
  private AnchorPane rootPane;
  User user;

  public void setUser(User user) {
    fullName.setText(user.getFirstName() + " " + user.getLastName());
    userId.setText(String.valueOf(user.getId()));
    email.setText(user.getEmail());
    role.setText(user.getRole().toString());
    gender.setText(user.getGender().toString());
    this.user = user;

  }

  @FXML
  private void handleLogout() {
    try {
      // Load the login screen
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/login.fxml"));
      Parent loginScreen = loader.load();

      // Get the current stage and set the new scene
      Stage stage = (Stage) rootPane.getScene().getWindow();
      stage.setScene(new Scene(loginScreen));
      stage.show();
    } catch (IOException e) {

    }
  }

}
