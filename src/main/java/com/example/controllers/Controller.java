package com.example.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Controller {

  private Stage stage;
  private Scene scene;
  private Parent root;

  public void redirect(ActionEvent event, String sceneName) throws IOException {
    root = FXMLLoader.load(getClass().getResource(sceneName));
    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    scene = new Scene(root);
    stage.setScene(scene);

    stage.show();
  }

}
