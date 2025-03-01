package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class BusController {

    @FXML
    private TextField busIdField;

    @FXML
    private TextField busAgencyField;

    @FXML
    private TextField busOriginField;

    @FXML
    private TextField busDestinationField;

    @FXML
    private Button closeButton;


    @FXML
    private void onSaveBus() {
        Bus bus = getBusFromFields(busIdField, busAgencyField, busOriginField,
                busDestinationField);
        if (bus != null) {
            if (Constraints.isDataExists(bus.getId(), "bus", "bus_id" )) {
                Constraints.showAlert("Error" ,"This data already exists!", Alert.AlertType.ERROR);
                Constraints.clearFiels(busAgencyField, busOriginField, busDestinationField);
            } else {
                if (!Constraints.isCorrectRange(busAgencyField, 16, 2) ) {
                    Constraints.showAlert("Error", "must: 2 <= Agency <= 16 !", Alert.AlertType.ERROR);
                }
                else if (!Constraints.isCorrectRange(busOriginField, 16, 2) ) {
                    Constraints.showAlert("Error" ,"must: 2 <= Origin <= 16 !", Alert.AlertType.ERROR);
                }
                else if (!Constraints.isCorrectRange(busDestinationField, 16, 2) ) {
                    Constraints.showAlert("Error" ,"must: 2 <= Origin <= 16 !", Alert.AlertType.ERROR);
                } else {
                    Crud.insertBus(bus.getId(), bus.getAgency(), bus.getOrigin(), bus.getDestination());
                    Constraints.clearFiels(busIdField, busAgencyField, busOriginField, busDestinationField);
                }
            }
        }
    }

    @FXML
    private void onFetchBus() {
        try {
            int bus_id = Integer.parseInt(busIdField.getText());
            Bus bus = Crud.getBusById(bus_id);

            if (bus != null) {
                busAgencyField.setText(bus.getAgency());
                busOriginField.setText(bus.getOrigin());
                busDestinationField.setText(bus.getDestination());
            } else {
                Constraints.showAlert("Not Found" ,"Bus not found!", Alert.AlertType.ERROR);
            }
        } catch (NumberFormatException e) {
            Constraints.showAlert("Invalid" ,"Invalid ID!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onUpdateBus() {
        Bus bus = getBusFromFields(busIdField, busAgencyField, busOriginField,
                busDestinationField);
        if(bus != null) {
            if (Constraints.isDataExists(bus.getId(), "bus", "bus_id" )) {
                if (!Constraints.isCorrectRange(busAgencyField, 16, 2) ) {
                    Constraints.showAlert("Error", "must: 2 <= Agency <= 16 !", Alert.AlertType.ERROR);
                }
                else if (!Constraints.isCorrectRange(busOriginField, 16, 2) ) {
                    Constraints.showAlert("Error" ,"must: 2 <= Origin <= 16 !", Alert.AlertType.ERROR);
                }
                else if (!Constraints.isCorrectRange(busDestinationField, 16, 2) ) {
                    Constraints.showAlert("Error" ,"must: 2 <= Origin <= 16 !", Alert.AlertType.ERROR);
                } else {
                    Crud.updateBus(bus.getId(), bus.getAgency(), bus.getOrigin(), bus.getDestination());
                    Constraints.clearFiels(busIdField, busAgencyField, busOriginField, busDestinationField);
                }
            } else {
                Constraints.showAlert("Error" ,"There is no data with id " + bus.getId() + " !", Alert.AlertType.ERROR);
                Constraints.clearFiels(busAgencyField, busOriginField, busDestinationField);
            }
        }
    }

    @FXML
    private void onDeleteBus() {
        try {
            int bus_id = Integer.parseInt(busIdField.getText());
            if (Constraints.isDataExists(bus_id, "bus", "bus_id" )) {
                Constraints.clearFiels(busIdField, busAgencyField, busOriginField, busDestinationField);
                Constraints.showDeleteConfirmation(bus_id, "bus");
            } else {
                Constraints.showAlert("ERROR" ,"There is no data with id " + bus_id + " !", Alert.AlertType.ERROR);
            }
        } catch (NumberFormatException e) {
            Constraints.showAlert("Error" ,"Invalid ID!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void busCloseButton() {
        Constraints.showCloseConfirmation("Cancel", "Do you want to Cancel?", Alert.AlertType.CONFIRMATION, closeButton);
    }

    private Bus getBusFromFields(TextField busIdField, TextField busAgencyField,
                                             TextField busOriginField, TextField busDestinationField) {
        try {
        return new Bus(
                Integer.parseInt(busIdField.getText()),
                busAgencyField.getText(),
                busOriginField.getText(),
                busDestinationField.getText());
        } catch(NumberFormatException e) {
            Constraints.showAlert("Invalid" ,"Please fill the blanks!", Alert.AlertType.ERROR);
            return null;
        }
    }
}