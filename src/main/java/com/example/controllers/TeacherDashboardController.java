package com.example.controllers;

import java.io.IOException;

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
  private void handleCreateQuiz() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/quiz_creation_form.fxml"));
    Parent dashboard = loader.load();
    QuizCreationFormController controller = loader.getController();
    controller.setUser(user);

    Scene scene = new Scene(dashboard);
    Stage stage = (Stage) rootPane.getScene().getWindow();

    stage.setScene(scene);
    stage.show();
  }
}