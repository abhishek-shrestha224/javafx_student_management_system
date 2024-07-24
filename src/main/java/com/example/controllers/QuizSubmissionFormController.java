package com.example.controllers;

import java.io.IOException;
import java.util.Arrays;

import com.example.exceptions.NotFoundException;
import com.example.models.Quiz;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class QuizSubmissionFormController extends DashboardController {
  @FXML
  private Pane rootPane;

  @FXML
  private Label quizIdLabel;

  @FXML
  private Label MCQ1Label;

  @FXML
  private RadioButton MCQ1Option1RadioButton;

  @FXML
  private RadioButton MCQ1Option2RadioButton;

  @FXML
  private RadioButton MCQ1Option3RadioButton;

  @FXML
  private RadioButton MCQ1Option4RadioButton;

  @FXML
  private Label MCQ2Label;

  @FXML
  private RadioButton MCQ2Option1RadioButton;

  @FXML
  private RadioButton MCQ2Option2RadioButton;

  @FXML
  private RadioButton MCQ2Option3RadioButton;

  @FXML
  private RadioButton MCQ2Option4RadioButton;

  @FXML
  private Label openEndedLabel;

  @FXML
  private TextArea openEndedAnswerTextArea;

  private QuizDataController quizDataController;

  @FXML
  private void initialize() {
    this.quizDataController = new QuizDataController();
  }

  public void loadQuiz(Integer quizId) {
    ToggleGroup toggleGroup1 = new ToggleGroup();
    ToggleGroup toggleGroup2 = new ToggleGroup();
    try {
      Quiz quiz = quizDataController.getQuizById(quizId);
      if (quiz == null) {
        throw new NotFoundException("Quiz with id " + quizId + " not found.");

      }

      quizIdLabel.setText("Quiz ID: " + quiz.getId());

      MCQ1Label.setText(quiz.getMcqQuestions()[0]);

      MCQ1Option1RadioButton.setText(quiz.getMcqOptions()[0][0]);
      MCQ1Option2RadioButton.setText(quiz.getMcqOptions()[0][1]);
      MCQ1Option3RadioButton.setText(quiz.getMcqOptions()[0][2]);
      MCQ1Option4RadioButton.setText(quiz.getMcqOptions()[0][3]);

      MCQ1Option1RadioButton.setToggleGroup(toggleGroup1);
      MCQ1Option2RadioButton.setToggleGroup(toggleGroup1);
      MCQ1Option3RadioButton.setToggleGroup(toggleGroup1);
      MCQ1Option4RadioButton.setToggleGroup(toggleGroup1);

      MCQ2Label.setText(quiz.getMcqQuestions()[1]);

      MCQ2Option1RadioButton.setText(quiz.getMcqOptions()[1][0]);
      MCQ2Option2RadioButton.setText(quiz.getMcqOptions()[1][1]);
      MCQ2Option3RadioButton.setText(quiz.getMcqOptions()[1][2]);
      MCQ2Option4RadioButton.setText(quiz.getMcqOptions()[1][3]);

      MCQ2Option1RadioButton.setToggleGroup(toggleGroup2);
      MCQ2Option2RadioButton.setToggleGroup(toggleGroup2);
      MCQ2Option3RadioButton.setToggleGroup(toggleGroup2);
      MCQ2Option4RadioButton.setToggleGroup(toggleGroup2);

      openEndedLabel.setText(quiz.getOpenEndedQuestion());
    } catch (NotFoundException err) {
      PopupController.showPopup(err.getErrorTitle(), err.getMessage());
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

  @FXML
  private void handleSubmit() {
    PopupController.showPopup("200-Sucess", "Submitted");
  }

}
