package com.example.controllers;

import java.io.IOException;

import com.example.exceptions.InvalidCredentialsException;
import com.example.exceptions.NotFoundException;
import com.example.models.Role;
import com.example.models.User;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class AuthController {

    @FXML
    private Pane rootPane;

    // @FXML
    // private AnchorPane loginPane;

    @FXML
    private TextField userId;

    @FXML
    private PasswordField password;

    // @FXML
    // private Button loginButton;

    @FXML
    private void handleLogin() {
        String userIdText = userId.getText();
        String passwordText = password.getText();

        try {
            if (authenticate(userIdText, passwordText)) {
                User user = getUser(userIdText);
                if (user != null) {
                    redirectToDashboard(user.getRole());
                }
            }
        } catch (InvalidCredentialsException e) {
            showAlert("Login Error", "Invalid credentials. Please check your user ID and password.");
        } catch (NotFoundException e) {
            showAlert("User Not Found", e.getMessage());
        } catch (IOException e) {
            showAlert("Error", "An error occurred while trying to load the dashboard.");
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
        // Stage stage = (Stage) rootPane.getScene().getWindow();
        // FXMLLoader loader;
        String fxmlFile;

        switch (role) {
            case ADMIN -> fxmlFile = "adminDashboard.fxml";
            case STUDENT -> fxmlFile = "studentDashboard.fxml";
            case TEACHER -> fxmlFile = "teacherDashboard.fxml";
            default -> throw new IllegalArgumentException("Unexpected role: " + role);
        }

        // loader = new FXMLLoader(getClass().getResource(fxmlFile));
        // AnchorPane dashboard = loader.load();
        // Scene scene = new Scene(dashboard);
        // stage.setScene(scene);
        // stage.show();
        System.out.println(fxmlFile);
    }

    private void showAlert(String title, String message) {
        // Alert alert = new Alert(AlertType.ERROR, message, ButtonType.OK);
        // alert.setTitle(title);
        // alert.setHeaderText(null);
        // alert.showAndWait();
        System.out.println(title);
        System.out.println(message);
    }
}
