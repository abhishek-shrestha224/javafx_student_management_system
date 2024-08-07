package com.example.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AdminDashboardController extends DashboardController {

  @FXML
  private Pane rootPane;

  @FXML
  private void handleViewComplaint() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/complaint_table_view.fxml"));
    Parent dashboard = loader.load();
    ComplaintTableViewController controller = loader.getController();
    controller.setUser(user);
    controller.loadUser();

    Scene scene = new Scene(dashboard);
    Stage stage = (Stage) rootPane.getScene().getWindow();

    stage.setScene(scene);
    stage.show();
  }

  @FXML
  private void handleManageTeachers() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/teachers_table_view.fxml"));
    Parent dashboard = loader.load();
    TeachersTableViewController controller = loader.getController();
    controller.setUser(user);

    Scene scene = new Scene(dashboard);
    Stage stage = (Stage) rootPane.getScene().getWindow();

    stage.setScene(scene);
    stage.show();
  }

  @FXML
  private void handleManageStudents() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/students_table_view.fxml"));
    Parent dashboard = loader.load();
    StudentsTableViewController controller = loader.getController();
    controller.setUser(user);

    Scene scene = new Scene(dashboard);
    Stage stage = (Stage) rootPane.getScene().getWindow();

    stage.setScene(scene);
    stage.show();
  }

}
