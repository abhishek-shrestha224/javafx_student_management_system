package com.example.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TeacherDashboardController extends DashboardController {
  @FXML
  private Pane rootPane;

  @FXML
  private void handleCreateQuiz(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/createQuiz.fxml"));
    Parent dashboard = loader.load();
    CreateQuizController controller = loader.getController();
    controller.setUser(user);

    Scene scene = new Scene(dashboard);
    Stage stage = (Stage) rootPane.getScene().getWindow();
    System.out.println(user.getUserId() + "DB");
    stage.setScene(scene);
    stage.show();
  }
}