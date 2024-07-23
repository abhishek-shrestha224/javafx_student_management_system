package com.example.controllers;

import java.io.IOException;

import com.example.exceptions.BadRequestException;
import com.example.exceptions.NotFoundException;
import com.example.exceptions.UnauthorizedException;
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

public class AuthController {

    @FXML
    private Pane rootPane;

    @FXML
    private TextField userId;

    @FXML
    private PasswordField password;

    @FXML
    private void handleLogin() throws BadRequestException {
        String userIdText = userId.getText();
        String passwordText = password.getText();

        try {
            if (validateForm(userIdText, passwordText)) {
                if (authenticate(Integer.parseInt(userIdText), passwordText)) {

                    UserDataController controller = new UserDataController();
                    User user = controller.getUserById(Integer.parseInt(userIdText));
                    if (user != null) {

                        redirectToDashboard(user.getRole(), user);
                    }

                }
            }
        } catch (UnauthorizedException e) {
            PopupController.showPopup("Login Error",
                    "Invalid credentials. Please check your user ID and password.");
        } catch (NotFoundException e) {
            PopupController.showPopup("User Not Found", e.getMessage());
        } catch (BadRequestException err) {
            PopupController.showPopup(err.getErrorTitle(), err.getMessage());

        } catch (IOException e) {
            PopupController.showPopup("Error",
                    "An error occurred while trying to load the dashboard: " + e.getMessage());
        }

    }

    private boolean validateForm(String userId, String password) throws BadRequestException {
        if (userId == null || userId.isEmpty()) {
            throw new BadRequestException("User ID is mandatory");
        }

        if (password == null || password.length() < 8) {
            throw new BadRequestException("Password must be at least 8 characters long.");
        }

        return true;

    }

    private boolean authenticate(int userId, String password) throws UnauthorizedException {
        UserDataController userDataController = new UserDataController();
        return userDataController.authenticate(userId, password);
    }

    private void redirectToDashboard(Role role, User user) throws IOException {
        String fxmlFile = null;

        switch (role) {
            case ADMIN:
                fxmlFile = "/views/admin_dashboard.fxml";
                break;
            case STUDENT:
                fxmlFile = "/views/student_dashboard.fxml";
                break;
            case TEACHER:
                fxmlFile = "/views/teacher_dashboard.fxml";
                break;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            System.out.println(loader);
            Parent dashboard = loader.load();

            if (role == Role.TEACHER) {
                TeacherDashboardController controller = loader.getController();
                controller.setUser(user);
            }
            if (role == Role.STUDENT) {
                StudentDashboardController controller = loader.getController();
                controller.setUser(user);
            }
            if (role == Role.ADMIN) {
                AdminDashboardController controller = loader.getController();
                controller.setUser(user);
            }
            Scene scene = new Scene(dashboard);
            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.err.println("Error loading FXML file: " + fxmlFile);
            throw e;
        } catch (ClassCastException e) {
            System.err.println("Casting error: " + e.getMessage());
            throw new RuntimeException("Unexpected user type: " + user.getClass().getName(), e);
        }
    }

}
