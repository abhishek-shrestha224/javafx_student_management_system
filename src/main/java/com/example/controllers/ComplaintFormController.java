package com.example.controllers;

import java.io.IOException;

import com.example.models.Complaint;

import com.example.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ComplaintFormController {
    @FXML
    private Pane rootPane;
    @FXML
    private TextField nameField;
    @FXML
    private TextArea complaintArea;

    private ComplaintDataController complaintDataController;

    private User user;

    public ComplaintFormController(User user) {
        this.user = user;
    }

    @FXML
    private void initialize() {
        complaintDataController = new ComplaintDataController(); // Initialize the ComplaintDataController
    }

    @FXML
    private void handleSubmit() {
        String name = nameField.getText().trim();
        String complaintText = complaintArea.getText().trim();

        if (name.isEmpty() || complaintText.isEmpty()) {
            // Show error message (use your own method for showing popups or alerts)
            PopupController.showPopup("Error", "Name and Complaint cannot be empty.");
            return;
        }

        int complaintId = complaintDataController.getNextComplaintId();
        Complaint complaint = new Complaint(complaintId, name, complaintText);

        complaintDataController.addComplaint(complaint);
        PopupController.showPopup("Success", "Complaint submitted successfully.");

        // Clear the form fields
        nameField.clear();
        complaintArea.clear();
    }

    @FXML
    private void handleBack() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/studentDashboard.fxml"));
        Parent dashboard = loader.load();

        StudentDashboardController controller = loader.getController();
        controller.setUser(user);
        Scene scene = new Scene(dashboard);
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
