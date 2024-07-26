package com.example.controllers;

import java.io.IOException;
import java.util.List;

import com.example.exceptions.BadRequestException;
import com.example.exceptions.NotFoundException;
import com.example.models.Evaluation;
import com.example.models.Role;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EvaluationFormController extends DashboardController {
  @FXML
  private Pane rootPane;

  @FXML
  private ComboBox<String> comboBox;

  @FXML
  private TextArea feedbackArea;

  private final UserDataController userDataController;
  private EvaluationDataController evaluationDataController;

  public EvaluationFormController() {
    this.userDataController = new UserDataController();
  }

  @FXML
  public void initialize() {
    loadStudentInfo();
    evaluationDataController = new EvaluationDataController();
  }

  private void loadStudentInfo() {
    List<String> studentsInfo = userDataController.getUserInfoByRole(Role.STUDENT);

    comboBox.getItems().clear(); // Clear previous items
    comboBox.getItems().addAll(studentsInfo); // Add new items
  }

  @FXML
  private void handleBack() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/teacher_dashboard.fxml"));
    Parent dashboard = loader.load();

    TeacherDashboardController controller = loader.getController();
    controller.setUser(user);
    Scene scene = new Scene(dashboard);
    Stage stage = (Stage) rootPane.getScene().getWindow();
    stage.setScene(scene);
    stage.show();
  }

  @FXML
  private void handleSubmit() throws NotFoundException, IOException {

    String selectedStudentInfo = comboBox.getValue();
    if (selectedStudentInfo == null || selectedStudentInfo.isEmpty()) {
      PopupController.showPopup("Validation Error", "Please select a student.");
      return;
    }

    Integer selectedStudentId = extractStudentId(selectedStudentInfo);
    String feedback = feedbackArea.getText().trim();

    try {
      if (validateForm(selectedStudentId, feedback)) {

        Evaluation evaluation = new Evaluation(feedback, userDataController.getUserById(selectedStudentId));
        evaluationDataController.createEvaluation(selectedStudentId, evaluation);

        PopupController.showPopup("200 Success", "Feedback for Student ID " + selectedStudentId + " submitted.");
      }

      FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/teacher_dashboard.fxml"));
      Parent dashboard = loader.load();

      TeacherDashboardController controller = loader.getController();
      controller.setUser(user);
      Scene scene = new Scene(dashboard);
      Stage stage = (Stage) rootPane.getScene().getWindow();
      stage.setScene(scene);
      stage.show();

    } catch (BadRequestException err) {
      PopupController.showPopup(err.getErrorTitle(), err.getMessage());
    } catch (IOException e) {
      PopupController.showPopup("404 Not Found", "Crutial Resources Missing!");
    }
  }

  private Integer extractStudentId(String studentInfo) {
    try {
      // Assuming the ID is the first 3 characters
      String idString = studentInfo.substring(0, 3);
      return Integer.valueOf(idString);
    } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
      PopupController.showPopup("Error", "Invalid Student ID format.");
      return null;
    }
  }

  private boolean validateForm(Integer studentId, String feedback) throws BadRequestException {
    if (studentId == null) {
      throw new BadRequestException("Student ID is required to evaluate.");
    }

    if (feedback.isEmpty()) {
      throw new BadRequestException("Feedback is required to evaluate.");
    }
    return true;
  }
}
