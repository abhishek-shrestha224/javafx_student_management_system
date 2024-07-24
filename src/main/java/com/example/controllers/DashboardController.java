package com.example.controllers;

import java.io.IOException;

import com.example.models.User;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

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
  private Pane rootPane;

  public User user;

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
      redirect(rootPane, "/views/login.fxml");

    } catch (IOException e) {
      PopupController.showPopup("500-Internal Server Error", "Something Went Wrong");

    }
  }

}
