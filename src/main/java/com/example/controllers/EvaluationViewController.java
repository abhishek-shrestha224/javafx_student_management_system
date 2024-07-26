package com.example.controllers;

import java.io.IOException;

import com.example.exceptions.ForbiddenException;
import com.example.exceptions.NotFoundException;
import com.example.models.Evaluation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EvaluationViewController extends DashboardController {
  @FXML
  private Pane rootPane;

  @FXML
  private Text feedbackText;

  private EvaluationDataController evaluationDataController;

  @FXML
  public void initialize() throws NotFoundException, ForbiddenException {
    this.evaluationDataController = new EvaluationDataController();
  }

  public void loadFeedback() throws IOException {
    try {
      Evaluation evaluation = evaluationDataController.getEvaluationByUserId(user.getId());
      System.out.println(evaluation.getFeedback());
      feedbackText.setText(evaluation.getFeedback());
    } catch (NotFoundException err) {
      feedbackText.setText("Teacher has not given you any feedback yet.");
    }
  }

  @FXML
  private void handleBack() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/student_dashboard.fxml"));
    Parent dashboard = loader.load();

    StudentDashboardController controller = loader.getController();
    controller.setUser(user);
    Scene scene = new Scene(dashboard);
    Stage stage = (Stage) rootPane.getScene().getWindow();
    stage.setScene(scene);
    stage.show();
  }
}
