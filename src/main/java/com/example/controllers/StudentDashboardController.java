package com.example.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StudentDashboardController extends DashboardController {
  @FXML
  private Pane rootPane;

  @FXML
  private void hanldeCreateComplaint() throws IOException {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/complaint_creation_form.fxml"));
      Parent dashboard = loader.load();
      ComplaintCreationFormController controller = loader.getController();
      controller.setUser(user);

      Scene scene = new Scene(dashboard);
      Stage stage = (Stage) rootPane.getScene().getWindow();
      stage.setScene(scene);
      stage.show();
    } catch (IOException e) {
      PopupController.showPopup("Error", "Crutial Resources Missing!");
    }
  }

}