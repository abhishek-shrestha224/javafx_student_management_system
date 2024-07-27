package com.example.controllers;

import java.io.IOException;

import com.example.models.Complaint;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ComplaintCreationFormController extends DashboardController {
    @FXML
    private Pane rootPane;
    @FXML
    private TextField titleField;
    @FXML
    private TextArea textField;

    private ComplaintDataController complaintDataController;

    @FXML
    private void initialize() {
        complaintDataController = new ComplaintDataController();
    }

    @FXML
    private void handleSubmit() throws IOException {
        try {
            String title = titleField.getText().trim();
            String text = textField.getText().trim();

            Complaint complaint = new Complaint(title, text);

            complaintDataController.createComplaint(complaint);
            PopupController.showPopup("200 Sucess", "Complaint created successfully.");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/student_dashboard.fxml"));
            Parent dashboard = loader.load();

            StudentDashboardController controller = loader.getController();
            controller.setUser(user);
            controller.loadUser();

            Scene scene = new Scene(dashboard);
            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            PopupController.showPopup("404 Not Found", "Crutial Resources Missing!");
        }
    }

    @FXML
    private void handleBack() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/student_dashboard.fxml"));
        Parent dashboard = loader.load();

        StudentDashboardController controller = loader.getController();
        controller.setUser(user);
        controller.loadUser();

        Scene scene = new Scene(dashboard);
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
