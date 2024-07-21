package com.example.controllers;

import com.example.exceptions.InvalidCredentialsException;
import com.example.models.Role;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class AuthController {

    @FXML
    private Pane rootPane;

    @FXML
    private AnchorPane loginPane;

    @FXML
    private TextField userId;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginButton;

    private final UserDataController userDataController;

    public AuthController() {
        this.userDataController = new UserDataController();
    }

    @FXML
    private void handleLogin() {
        String userIdText = userId.getText();
        String passwordText = password.getText();

        System.out.println("User ID: " + userIdText);
        System.out.println("Password: " + passwordText);

        try {
            if (userDataController.authenticate(userIdText, passwordText)) {
                System.out.println("Login successful!");
                Role userRole = userDataController.getUserRole(userIdText);
                System.out.println("User Role: " + userRole);

            }
        } catch (InvalidCredentialsException e) {
            System.out.println("Login failed. Invalid credentials.");

        }
    }
}
