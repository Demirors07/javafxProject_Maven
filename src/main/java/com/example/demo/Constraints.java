package com.example.demo;

import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class Constraints {

    public static void clearFiels(TextField... fields) {
        for (TextField textField : fields) {
            textField.clear();
        }
    }

    public static void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showAlert(String title, String message, Alert.AlertType type, Button close) {
        Alert alert = new Alert(type, message, ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.setTitle(title);
        alert.setHeaderText(null);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            Stage stage = (Stage) close.getScene().getWindow();
            stage.close();
        }
    }

    public static void showDeleteConfirmation(int id, String whichDatabase) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "ID: " + id, ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.setTitle("Confirmation of Deletion");
        alert.setHeaderText("Are you sure, Do you want to Delete?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            if (whichDatabase.equals("passenger"))
                Crud.deletePassenger(id);
            else if (whichDatabase.equals("bus"))
                Crud.deleteBus(id);
        }
    }

    public static boolean isDataExists(int id, String whichDatabase, String id_RowName) {
        String sql = "SELECT COUNT(*) FROM " + whichDatabase + " WHERE " + id_RowName + " = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isCorrectRange(TextField tf, int maxLength, int minLength) {
        return tf.getText().trim().length() <= maxLength || minLength >= tf.getText().trim().length();
    }


}
