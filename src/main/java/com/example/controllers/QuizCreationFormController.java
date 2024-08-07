package com.example.controllers;

import java.io.IOException;

import com.example.exceptions.BadRequestException;
import com.example.exceptions.ForbiddenException;
import com.example.models.Quiz;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class QuizCreationFormController extends DashboardController {
  @FXML
  private Pane rootPane;

  @FXML
  private TextField mcq1Question;
  @FXML
  private TextField mcq1OptionA;
  @FXML
  private TextField mcq1OptionB;
  @FXML
  private TextField mcq1OptionC;
  @FXML
  private TextField mcq1OptionD;

  @FXML
  private TextField mcq2Question;
  @FXML
  private TextField mcq2OptionA;
  @FXML
  private TextField mcq2OptionB;
  @FXML
  private TextField mcq2OptionC;
  @FXML
  private TextField mcq2OptionD;

  @FXML
  private TextField openEndedQuestion;

  private QuizDataController quizDataController;

  @FXML
  private void initialize() {
    quizDataController = new QuizDataController(); // Initialize the QuizDataController
  }

  @FXML
  private void handleSubmit() throws BadRequestException, ForbiddenException {

    try {
      String[] mcqQuestions = {
          mcq1Question.getText().trim(),
          mcq2Question.getText().trim()
      };

      String[][] mcqOptions = {
          {
              mcq1OptionA.getText().trim(),
              mcq1OptionB.getText().trim(),
              mcq1OptionC.getText().trim(),
              mcq1OptionD.getText().trim()
          },
          {
              mcq2OptionA.getText().trim(),
              mcq2OptionB.getText().trim(),
              mcq2OptionC.getText().trim(),
              mcq2OptionD.getText().trim()
          }
      };
      String openEnded = openEndedQuestion.getText().trim();
      if (validateForm(mcqQuestions, mcqOptions, openEnded)) {
        Quiz quiz = new Quiz(mcqQuestions, mcqOptions, openEnded);
        // quizDataController.addUser(user);
        quizDataController.addQuiz(quiz, user.getId());
        PopupController.showPopup("200 Sucess", "Quiz Created Sucessfully.");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/teacher_dashboard.fxml"));
        Parent dashboard = loader.load();

        TeacherDashboardController controller = loader.getController();
        controller.setUser(user);
        controller.loadUser();

        Scene scene = new Scene(dashboard);
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
      }

    } catch (BadRequestException err) {
      PopupController.showPopup(err.getErrorTitle(), err.getMessage());
    } catch (ForbiddenException err) {
      PopupController.showPopup(err.getErrorTitle(), err.getMessage());
    } catch (IOException e) {
      PopupController.showPopup("404 Not Found", "Crutial Resources Missing!");
    }
  }

  @FXML
  private void handleBack() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/teacher_dashboard.fxml"));
    Parent dashboard = loader.load();

    TeacherDashboardController controller = loader.getController();
    controller.setUser(user);
    controller.loadUser();

    Scene scene = new Scene(dashboard);
    Stage stage = (Stage) rootPane.getScene().getWindow();
    stage.setScene(scene);
    stage.show();
  }

  private boolean validateForm(String[] mcqQuestions, String[][] mcqOptions, String openEnded)
      throws BadRequestException {

    // Validate MCQ questions
    for (String question : mcqQuestions) {
      if (question.isEmpty()) {
        throw new BadRequestException("All MCQ questions must be filled out.");
      }
    }

    // Validate MCQ options
    for (String[] options : mcqOptions) {
      if (options.length != 4) {
        throw new BadRequestException("Each MCQ must have exactly 4 options.");
      }
      for (String option : options) {
        if (option.isEmpty()) {
          throw new BadRequestException("All options for each MCQ must be filled out.");
        }
      }
    }

    // Validate open-ended question
    if (openEnded.isEmpty()) {
      throw new BadRequestException("Open-ended question must be filled out.");
    }

    return true;
  }

}