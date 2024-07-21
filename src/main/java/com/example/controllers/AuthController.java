package com.example.controllers;

import java.io.IOException;

import com.example.exceptions.InvalidCredentialsException;
import com.example.exceptions.NotFoundException;
import com.example.models.Role;
import com.example.models.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AuthController extends Controller {

    @FXML
    private Pane rootPane;

    @FXML
    private TextField userId;

    @FXML
    private PasswordField password;

    @FXML
    private void handleLogin() {
        String userIdText = userId.getText();
        String passwordText = password.getText();

        if (validateForm(userIdText, passwordText)) {
            try {
                if (authenticate(userIdText, passwordText)) {
                    User user = getUser(userIdText);
                    if (user != null) {
                        redirectToDashboard(user.getRole());
                    }
                }
            } catch (InvalidCredentialsException e) {
                PopupController.showPopup("Login Error",
                        "Invalid credentials. Please check your user ID and password.");
            } catch (NotFoundException e) {
                PopupController.showPopup("User Not Found", e.getMessage());
            } catch (IOException e) {
                PopupController.showPopup("Error",
                        "An error occurred while trying to load the dashboard: " + e.getMessage());
            }
        }
    }

    private boolean authenticate(String userId, String password) throws InvalidCredentialsException {
        UserDataController userDataController = new UserDataController();
        return userDataController.authenticate(userId, password);
    }

    private User getUser(String userId) throws NotFoundException {
        UserDataController userDataController = new UserDataController();
        return userDataController.getUserById(userId);
    }

    private void redirectToDashboard(Role role) throws IOException {
        String fxmlFile;

        switch (role) {
            case ADMIN -> fxmlFile = "/views/adminDashboard.fxml";
            case STUDENT -> fxmlFile = "/views/studentDashboard.fxml";
            case TEACHER -> fxmlFile = "/views/teacherDashboard.fxml";
            default -> throw new IllegalArgumentException("Unexpected role: " + role);
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent dashboard = loader.load();
            Scene scene = new Scene(dashboard);
            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading FXML file: " + fxmlFile);
            throw e; // Re-throw the exception after logging
        }
    }

    private boolean validateForm(String userId, String password) {
        if (userId == null || userId.isEmpty()) {
            PopupController.showPopup("Validation Error", "User ID cannot be empty.");
            return false;
        }

        if (password == null || password.length() < 8) {
            PopupController.showPopup("Validation Error", "Password must be at least 8 characters long.");
            return false;
        }

        return true;
    }
}
