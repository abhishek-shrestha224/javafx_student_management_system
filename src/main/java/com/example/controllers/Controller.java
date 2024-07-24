package com.example.controllers;

import java.io.IOException;

import com.example.models.User;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Controller {

  public void redirect(Pane rootPane, String fxmlPath) throws IOException {
    // Load the login screen
    FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
    Parent root = loader.load();

    // Get the current stage and set the new scene
    Stage stage = (Stage) rootPane.getScene().getWindow();
    stage.setScene(new Scene(root));
    stage.show();
  }

}
