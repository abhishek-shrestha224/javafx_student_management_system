package com.example.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.example.models.Complaint;
import com.example.models.Role;
import com.example.models.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ComplaintTableViewController extends DashboardController implements Initializable {

  @FXML
  private Pane rootPane;

  @FXML
  private TableView<Complaint> complaintTable;

  @FXML
  private TableColumn<Complaint, String> titleCol;

  @FXML
  private TableColumn<Complaint, String> complaintCol;

  private final ComplaintDataController complaintDataController = new ComplaintDataController();

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
    complaintCol.setCellValueFactory(new PropertyValueFactory<>("text"));

    loadComplaints();
    complaintTable.getStylesheets().add(getClass().getResource("/styles/app.css").toExternalForm());
  }

  private void loadComplaints() {
    List<Complaint> complaints = complaintDataController.getAllComplaints();
    ObservableList<Complaint> complaintData = FXCollections.observableArrayList(complaints);
    complaintTable.setItems(complaintData);
  }

  @FXML
  private void handleBack() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/admin_dashboard.fxml"));
      Parent dashboard = loader.load();

      AdminDashboardController controller = loader.getController();
      controller.setUser(user);
      controller.loadUser();

      Scene scene = new Scene(dashboard);
      Stage stage = (Stage) rootPane.getScene().getWindow();
      stage.setScene(scene);
      stage.show();
    } catch (IOException err) {
      PopupController.showPopup("500-Internal Server Error", "Something Went Wrong");
    }

  }

}
