package com.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginController {

        @FXML
        private Pane rootPane;
        @FXML
        private AnchorPane loginPane;

        @FXML
        private TextField usernameField;

        @FXML
        private PasswordField passwordField;

        @FXML
        private Button loginButton;

        @FXML
        public void initialize() {
            // Bind the layout properties of loginPane to center it within rootPane
            loginPane.layoutXProperty().bind(rootPane.widthProperty().subtract(loginPane.widthProperty()).divide(2));
            loginPane.layoutYProperty().bind(rootPane.heightProperty().subtract(loginPane.heightProperty()).divide(2));
        }


        @FXML
        private void handleLoginButtonAction() {
            String username = usernameField.getText();
            String password = passwordField.getText();

            try {
                FXMLLoader loader = new FXMLLoader();
                Stage stage = (Stage) loginButton.getScene().getWindow();
                Scene scene;
                if (username.equals("STD") && password.equals("1234")) {
                    loader.setLocation(getClass().getResource("studentDashboard.fxml"));
                    scene = new Scene(loader.load(), 600, 400);
                    stage.setScene(scene);
                    stage.setTitle("Student Dashboard");
                } else if (username.equals("ADM") && password.equals("1234")) {
                    loader.setLocation(getClass().getResource("adminDashboard.fxml"));
                    scene = new Scene(loader.load(), 600, 400);
                    stage.setScene(scene);
                    stage.setTitle("Admin Dashboard");
                } else if (username.equals("TCR") && password.equals("1234")) {
                    loader.setLocation(getClass().getResource("teacherDashboard.fxml"));
                    scene = new Scene(loader.load(), 600, 400);
                    stage.setScene(scene);
                    stage.setTitle("Teacher Dashboard");
                }else if (username.equals("PCR") && password.equals("1234")) {
                    loader.setLocation(getClass().getResource("coordinatorDashboard.fxml"));
                    scene = new Scene(loader.load(), 600, 400);
                    stage.setScene(scene);
                    stage.setTitle("Coordinator Dashboard");
                }
                else {
                    System.out.println("Invalid credentials");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
