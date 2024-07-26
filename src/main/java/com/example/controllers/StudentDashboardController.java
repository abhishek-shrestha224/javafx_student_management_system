package com.example.controllers;

import java.io.IOException;

import com.example.exceptions.ForbiddenException;
import com.example.exceptions.NotFoundException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StudentDashboardController extends DashboardController {
  @FXML
  private Pane rootPane;

  @FXML
  private void hanldeCreateComplaint() throws IOException {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/complaint_creation_form.fxml"));
      Parent root = loader.load();
      ComplaintCreationFormController controller = loader.getController();
      controller.setUser(user);

      Scene scene = new Scene(root);
      Stage stage = (Stage) rootPane.getScene().getWindow();
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      PopupController.showPopup("Error", "Crutial Resources Missing!");
    }
  }

  @FXML
  private void handleTakeQuiz() throws IOException, NotFoundException, ForbiddenException {

    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/quiz_attempt_portal.fxml"));
      Parent root = loader.load();
      QuizAttempPortalController controller = loader.getController();
      controller.setUser(user);
      controller.loadQuizzes();
      Scene scene = new Scene(root);
      Stage stage = (Stage) rootPane.getScene().getWindow();
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      PopupController.showPopup("Error", "Crutial Resources Missing!");
    }
  }

  @FXML
  private void handleViewFeedback() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/evaluation_view.fxml"));
      Parent root = loader.load();
      EvaluationViewController controller = loader.getController();
      controller.setUser(user);
      controller.loadFeedback();
      Scene scene = new Scene(root);
      Stage stage = (Stage) rootPane.getScene().getWindow();
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      PopupController.showPopup("Error", "Crutial Resources Missing!");
    }

  }
}