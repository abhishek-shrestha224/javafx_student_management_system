package com.example;

import java.io.IOException;

import com.example.exceptions.NotFoundException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

  @Override
  public void start(Stage stage) throws IOException, NotFoundException {

    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/login.fxml"));
    Parent root = loader.load();

    Scene scene = new Scene(root);
    Image icon = new Image(getClass().getResourceAsStream("/images/icon.png"));
    stage.getIcons().add(icon);
    scene.getStylesheets().add(getClass().getResource("/styles/app.css").toExternalForm());

    stage.setScene(scene);

    stage.show();

  }

  public static void main(String[] args) {
    launch();
  }

}