package com.example.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.example.models.Role;
import com.example.models.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TeachersTableViewController extends DashboardController implements Initializable {

  @FXML
  private Pane rootPane;

  @FXML
  private TableView<User> teachersTable;

  @FXML
  private TableColumn<User, Integer> idCol;

  @FXML
  private TableColumn<User, String> firstNameCol;

  @FXML
  private TableColumn<User, String> lastNameCol;

  @FXML
  private TableColumn<User, String> emailCol;

  @FXML
  private TableColumn<User, String> genderCol;

  @FXML
  private TableColumn<User, String> roleCol;

  @FXML
  private TableColumn<User, Void> editCol;

  @FXML
  private TableColumn<User, Void> deleteCol;

  private final UserDataController userDataController = new UserDataController();

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // Set up the columns in the table
    idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
    genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
    roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));

    // Add edit and delete buttons
    addButtonToTable();

    // Load teacher data
    loadTeachers();
    teachersTable.getStylesheets().add(getClass().getResource("/styles/app.css").toExternalForm());
  }

  private void loadTeachers() {
    List<User> teachers = userDataController.getUsersByRole(Role.TEACHER);
    ObservableList<User> teacherData = FXCollections.observableArrayList(teachers);
    teachersTable.setItems(teacherData);
  }

  private void addButtonToTable() {

    Callback<TableColumn<User, Void>, TableCell<User, Void>> editCellFactory = (
        final TableColumn<User, Void> param) -> {
      final TableCell<User, Void> cell = new TableCell<User, Void>() {
        private final Button btn = new Button("Edit");

        {
          btn.setOnAction((event) -> {
            User workingUser = getTableView().getItems().get(getIndex());
            handleEdit(workingUser);
          });
        }

        @Override
        public void updateItem(Void item, boolean empty) {
          super.updateItem(item, empty);
          if (empty) {
            setGraphic(null);
          } else {
            setGraphic(btn);
          }
        }
      };
      return cell;
    };

    // Add Delete Button
    Callback<TableColumn<User, Void>, TableCell<User, Void>> deleteCellFactory = (
        final TableColumn<User, Void> param) -> {
      final TableCell<User, Void> cell = new TableCell<User, Void>() {
        private final Button btn = new Button("Delete");

        {
          btn.setOnAction((event) -> {
            User workingUser = getTableView().getItems().get(getIndex());
            handleDelete(workingUser);
          });
        }

        @Override
        public void updateItem(Void item, boolean empty) {
          super.updateItem(item, empty);
          if (empty) {
            setGraphic(null);
          } else {
            setGraphic(btn);
          }
        }
      };
      return cell;
    };

    editCol.setCellFactory(editCellFactory);
    deleteCol.setCellFactory(deleteCellFactory);
  }

  private void handleEdit(User workingUser) {
    // Implement your edit logic here
    System.out.println("Edit " + workingUser.getId());
  }

  private void handleDelete(User workingUser) {
    // Implement your delete logic here
    System.out.println("Delete " + workingUser.getId());
  }

  @FXML
  private void handleAdd() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/teachers_creation_form.fxml"));
      Parent root = loader.load();

      TeachersCreationFormController controller = loader.getController();
      controller.setUser(user);

      Scene scene = new Scene(root);
      Stage stage = (Stage) rootPane.getScene().getWindow();
      stage.setScene(scene);
      stage.show();
    } catch (IOException err) {
      PopupController.showPopup("500-Internal Server Error", "Something Went Wrong.");
    }

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
