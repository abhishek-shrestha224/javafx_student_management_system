package com.example.controllers;

import com.example.models.Quiz;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

import com.example.exceptions.ForbiddenException;
import com.example.exceptions.NotFoundException;

public class QuizAttempPortalController extends DashboardController {
    @FXML
    private Pane rootPane;

    @FXML
    private VBox quizButtonContainer;

    private final QuizDataController quizDataController;
    private final UserDataController userDataController;

    public QuizAttempPortalController() {
        this.quizDataController = new QuizDataController();
        this.userDataController = new UserDataController();
    }

    @FXML
    public void initialize() throws NotFoundException, ForbiddenException {
        user = userDataController.getUserById(270);
        loadQuizzes();
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

    private void loadQuizzes() throws NotFoundException, ForbiddenException {
        try {
            Map<Integer, Quiz> quizzes = quizDataController.getAllQuizzes(user.getId());

            quizButtonContainer.getChildren().clear();

            for (Map.Entry<Integer, Quiz> entry : quizzes.entrySet()) {
                Integer quizId = entry.getKey();

                Button quizButton = new Button(String.valueOf(quizId)); // Display first question as button label
                // quizButton.setOnAction(event ->
                // handleQuizButtonClick(String.valueOf(quizId)));

                quizButtonContainer.getChildren().add(quizButton);
            }
        } catch (NotFoundException err) {
            PopupController.showPopup(err.getErrorTitle(), err.getMessage());
        } catch (ForbiddenException err) {
            PopupController.showPopup(err.getErrorTitle(), err.getMessage());
        }

    }

}
