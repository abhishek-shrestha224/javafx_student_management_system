package com.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ConfirmationPopupController {

  @FXML
  private Button noButton;

  @FXML
  private Button yesButton;

  private Runnable onConfirm;

  @FXML
  private void initialize() {
    yesButton.setOnAction(event -> {
      if (onConfirm != null) {
        onConfirm.run();
      }
      closePopup();
    });

    noButton.setOnAction(event -> closePopup());
  }

  public void setOnConfirm(Runnable onConfirm) {
    this.onConfirm = onConfirm;
  }

  private void closePopup() {
    Stage stage = (Stage) noButton.getScene().getWindow();
    stage.close();
  }
}
