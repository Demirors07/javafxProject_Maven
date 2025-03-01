package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;


public class PassengerController {

    @FXML
    private TextField passengerIdField;

    @FXML
    private TextField passengerNameField;

    @FXML
    private TextField passengerAdressField;

    @FXML
    private TextField passengerTelField;


    @FXML
    private void onSavePassenger() {
        Passenger passenger = getPassengerFromFields(passengerIdField, passengerNameField, passengerAdressField,
                passengerTelField);
        if (passenger != null) {
            if (Constraints.isDataExists(passenger.getId(), "passenger", "pass_id" )) {
                Constraints.showAlert("Error" ,"This data already exists!", Alert.AlertType.ERROR);
                Constraints.clearFiels(passengerNameField, passengerAdressField, passengerTelField);
            } else {
                if (!Constraints.isCorrectRange(passengerNameField, 16, 2) ) {
                    Constraints.showAlert("Error", "must: 2 < Name <= 16 !", Alert.AlertType.ERROR);
                }
                else if (!Constraints.isCorrectRange(passengerAdressField, 32, 5) ) {
                    Constraints.showAlert("Error" ,"must: 5 < Adress <= 32 !", Alert.AlertType.ERROR);
                }
                else if (passengerTelField.getText().length() != 11 ) {
                    Constraints.showAlert("Error" ,"Tel must be 11 char", Alert.AlertType.ERROR);
                } else {
                    Crud.insertPassenger(passenger.getId(), passenger.getName(), passenger.getAdress(), passenger.getTel());
                    Constraints.clearFiels(passengerIdField, passengerNameField, passengerAdressField, passengerTelField);
                }
            }
        }
    }

    @FXML
    private void onFetchPassenger() {
        try {
            int pass_id = Integer.parseInt(passengerIdField.getText());
            Passenger passenger = Crud.getPassengerById(pass_id);

            if (passenger != null) {
                passengerNameField.setText(passenger.getName());
                passengerAdressField.setText(passenger.getAdress());
                passengerTelField.setText(passenger.getTel());
            } else {
                Constraints.showAlert("Not Found" ,"Passenger not found!", Alert.AlertType.INFORMATION);
            }
        } catch (NumberFormatException e) {
            Constraints.showAlert("Not Found" ,"Invalid ID!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onUpdatePassenger() {
        Passenger passenger = getPassengerFromFields(passengerIdField, passengerNameField, passengerAdressField,
                passengerTelField);
        if (passenger != null) {
            if (Constraints.isDataExists(passenger.getId(), "passenger", "pass_id" )) {
                if (!Constraints.isCorrectRange(passengerNameField, 16, 2) ) {
                    Constraints.showAlert("Error", "must: 2 < Name <= 16 !", Alert.AlertType.ERROR);
                }
                else if (!Constraints.isCorrectRange(passengerAdressField, 32, 5) ) {
                    Constraints.showAlert("Error" ,"must: 5 < Adress <= 32 !", Alert.AlertType.ERROR);
                }
                else if (passengerTelField.getText().trim().length() != 11 ) {
                    Constraints.showAlert("Error" ,"must: 11 char length", Alert.AlertType.ERROR);
                } else {
                    Crud.updatePassenger(passenger.getId(), passenger.getName(), passenger.getAdress(), passenger.getTel());
                    Constraints.clearFiels(passengerIdField, passengerNameField, passengerAdressField, passengerTelField);
                }
            } else {
                Constraints.showAlert("ERROR" ,"There is no data with id " + passenger.getId() + " !", Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    private void onDeletePassenger() {
        try {
            int pass_id = Integer.parseInt(passengerIdField.getText());
            if (Constraints.isDataExists(pass_id, "passenger", "pass_id" )) {
                Constraints.clearFiels(passengerIdField, passengerNameField, passengerAdressField, passengerTelField);
                Constraints.showDeleteConfirmation(pass_id, "passenger");
            } else {
                Constraints.showAlert("ERROR" ,"There is no data with id " + pass_id + " !", Alert.AlertType.ERROR);
            }
        } catch (NumberFormatException e) {
            Constraints.showAlert("Error" ,"Invalid ID!", Alert.AlertType.ERROR);
        }

    }

    @FXML
    private void passNextButton() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("bus-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage secondStage = new Stage();
            secondStage.setTitle("DIMS Project");
            secondStage.setScene(new Scene(root, 500, 300));
            Image icon = new Image(getClass().getResourceAsStream("/assets/bus_icon.png"));
            secondStage.getIcons().add(icon);
            Stage stage = (Stage) passengerIdField.getScene().getWindow();
            stage.close();
            secondStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Passenger getPassengerFromFields(TextField passengerIdField, TextField passengerNameField,
                                             TextField passengerAdressField, TextField passengerTelField) {
        try {
        return new Passenger(
                Integer.parseInt(passengerIdField.getText()),
                passengerNameField.getText(),
                passengerAdressField.getText(),
                passengerTelField.getText());
        } catch (NumberFormatException e) {
            Constraints.showAlert("Error" ,"Please fill the blanks!", Alert.AlertType.ERROR);
            return null;
        }
    }




}