package com.example.demo;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Crud {
    // Bus insertion in PostgreSQL
    public static void insertPassenger(int pass_id, String name, String adress, String tel) {
        String sql = "INSERT INTO passenger (pass_id, name, adress, tel) VALUES (?, ?, ?, ?)";

        try(Connection conn = DatabaseConnection.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, pass_id);
            pstmt.setString(2, name);
            pstmt.setString(3, adress);
            pstmt.setString(4, tel);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                Constraints.showAlert("Added", "Record Successfully Added!", Alert.AlertType.INFORMATION);
            } else {
                Constraints.showAlert("Fail", "Add Failed!", Alert.AlertType.INFORMATION);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Getting Passenger from PostgreSQL
    public static Passenger getPassengerById(int id) {
        String sql = "SELECT * FROM passenger WHERE pass_id = ?";
        Passenger passenger = null;

        try (Connection conn = DatabaseConnection.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int pass_id = rs.getInt("pass_id");
                String name = rs.getString("name");
                String adress = rs.getString("adress");
                String tel = rs.getString("tel");

                passenger = new Passenger(pass_id, name, adress, tel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passenger;
    }

    // Passenger updating in PostgreSQL
    public static void updatePassenger(int id, String newName, String newAdress, String newTel) {
        String sql = "UPDATE passenger SET name = ?, adress = ?, tel = ? WHERE pass_id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newName);
            pstmt.setString(2, newAdress);
            pstmt.setString(3, newTel);
            pstmt.setInt(4, id);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                Constraints.showAlert("Updated", "Record Successfully Updated!", Alert.AlertType.INFORMATION);
            } else {
                Constraints.showAlert("Fail", "Update Failed!", Alert.AlertType.INFORMATION);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Passenger deleting from PostgreSQL
    public static void deletePassenger(int id) {
        String sql = "DELETE FROM passenger WHERE pass_id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                Constraints.showAlert("Deleted", "Record Successfully Deleted!", Alert.AlertType.INFORMATION);
            } else {
                Constraints.showAlert("Fail", "Delete Failed!", Alert.AlertType.INFORMATION);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Bus insertion in PostgreSQL
    public static void insertBus(int bus_id, String agency, String origin, String destination) {
        String sql = "INSERT INTO bus (bus_id, agency, origin, destination) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bus_id);
            pstmt.setString(2, agency);
            pstmt.setString(3, origin);
            pstmt.setString(4, destination);


            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                Constraints.showAlert("Added", "Record Successfully Added!", Alert.AlertType.INFORMATION);
            } else {
                Constraints.showAlert("Fail", "Add Failed!", Alert.AlertType.INFORMATION);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Hata detayları terminale yazdırılır.
        }
    }

    // Getting Bus from PostgreSQL
    public static Bus getBusById(int id) {
        String sql = "SELECT * FROM bus WHERE bus_id = ?";
        Bus bus = null;

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                bus = new Bus(rs.getInt("bus_id"), rs.getString("agency"),
                        rs.getString("origin"), rs.getString("destination"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bus;
    }

    // Deleting Bus Values From PostgreSQL
    public static void deleteBus(int id) {
        String sql = "DELETE FROM bus WHERE bus_id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                Constraints.showAlert("Deleted", "Record Successfully Deleted!", Alert.AlertType.INFORMATION);
            } else {
                Constraints.showAlert("Fail", "Delete Failed!", Alert.AlertType.INFORMATION);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Updating Passenger Values
    public static void updateBus(int bus_id, String agency, String origin, String destination) {
        String sql = "UPDATE bus SET agency = ?, origin = ?, destination = ? WHERE bus_id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, agency);
            pstmt.setString(2, origin);
            pstmt.setString(3, destination);
            pstmt.setInt(4, bus_id);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                Constraints.showAlert("Updated!", "Record Successfully Updated !", Alert.AlertType.INFORMATION);
            } else {
                Constraints.showAlert("Fail", "Update Failed!", Alert.AlertType.INFORMATION);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
