package com.example.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopupController {
  @FXML
  private Label warningTitle;

  @FXML
  private Label warningMessage;

  @FXML
  private Button closeButton;

  @FXML
  private void initialize() {
    closeButton.setOnAction(event -> ((Stage) closeButton.getScene().getWindow()).close());
  }

  public void setWarningMessage(String message) {
    warningMessage.setText(message);
  }

  public void setWarningTitle(String title) {
    warningTitle.setText(title);
  }

  public static void showPopup(String title, String message) {
    try {
      FXMLLoader loader = new FXMLLoader(PopupController.class.getResource("/views/popup.fxml"));
      Parent root = loader.load();

      PopupController popupController = loader.getController();
      popupController.setWarningTitle(title);
      popupController.setWarningMessage(message);

      Stage stage = new Stage();
      stage.setScene(new Scene(root));
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setTitle("Popup");
      stage.show();
    } catch (IOException e) {
    }
  }
}
