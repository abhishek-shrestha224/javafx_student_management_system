package com.example.controllers;

import java.io.IOException;
import java.util.Map;

import com.example.exceptions.ForbiddenException;
import com.example.exceptions.NotFoundException;
import com.example.models.Quiz;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class QuizAttempPortalController extends DashboardController {
    @FXML
    private Pane rootPane;

    @FXML
    private VBox quizButtonContainer;

    private QuizDataController quizDataController;

    @FXML
    public void initialize() throws NotFoundException, ForbiddenException {
        this.quizDataController = new QuizDataController();

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

    public void loadQuizzes() throws NotFoundException, ForbiddenException {
        try {
            Map<Integer, Quiz> quizzes = quizDataController.getAllQuizzes(user.getId());

            quizButtonContainer.getChildren().clear();

            for (Map.Entry<Integer, Quiz> entry : quizzes.entrySet()) {
                Integer quizId = entry.getKey();

                Button quizButton = new Button("Quiz-" + String.valueOf(quizId));
                quizButton.setPrefHeight(36.0);
                quizButton.setPrefWidth(126.0);
                quizButton.setStyle(
                        "-fx-background-color: #2c2a33; -fx-text-fill: WHITE; -fx-font-size: 12.0; -fx-font-weight: bold;");
                quizButton.setLayoutX(213.0);
                quizButton.setLayoutY(114.0);
                quizButton.setOnAction(event -> handleTakeQuiz(quizId));

                quizButtonContainer.getChildren().add(quizButton);
            }
        } catch (NotFoundException err) {
            PopupController.showPopup(err.getErrorTitle(), err.getMessage());
        } catch (ForbiddenException err) {
            PopupController.showPopup(err.getErrorTitle(), err.getMessage());
        }
    }

    @FXML
    private void handleTakeQuiz(Integer quizId) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/quiz_submission_form.fxml"));
            Parent dashboard = loader.load();
            QuizSubmissionFormController controller = loader.getController();
            controller.setUser(user);
            controller.loadQuiz(quizId);
            Scene scene = new Scene(dashboard);
            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            PopupController.showPopup("Error", "Crucial Resources Missing!");
        }
    }

}