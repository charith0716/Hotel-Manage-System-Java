/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Reservation;
import utils.DBConnection;

public class ReservationService {
    private Connection connection;

    // Constructor
    public ReservationService() {
        // Set the connection using DBConnection class
        this.connection = DBConnection.getConnection();
    }

    // Add a method to set the connection
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    // Create
    public void addReservation(Reservation reservation) {
        String query = "INSERT INTO Reservation (reservationId, roomId, customerId, checkInDate, checkOutDate, packageType, amount) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, reservation.getReservationId());
            statement.setInt(2, reservation.getRoomId());
            statement.setInt(3, reservation.getCustomerId());
            statement.setDate(4, new java.sql.Date(reservation.getCheckInDate().getTime()));
            statement.setDate(5, new java.sql.Date(reservation.getCheckOutDate().getTime()));
            statement.setString(6, reservation.getPackageType());
            statement.setDouble(7, reservation.getAmount());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();

        String query = "SELECT * FROM Reservation";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Reservation reservation = new Reservation(
                        resultSet.getInt("reservationId"),
                        resultSet.getInt("roomId"),
                        resultSet.getInt("customerId"),
                        resultSet.getDate("checkInDate"),
                        resultSet.getDate("checkOutDate"),
                        resultSet.getString("packageType"),
                        resultSet.getDouble("amount")
                );
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }

    // Update
    public void updateReservation(Reservation reservation) {
        String query = "UPDATE Reservation SET roomId = ?, customerId = ?, checkInDate = ?, " +
                "checkOutDate = ?, packageType = ?, amount = ? WHERE reservationId = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, reservation.getRoomId());
            statement.setInt(2, reservation.getCustomerId());
            statement.setDate(3, new java.sql.Date(reservation.getCheckInDate().getTime()));
            statement.setDate(4, new java.sql.Date(reservation.getCheckOutDate().getTime()));
            statement.setString(5, reservation.getPackageType());
            statement.setDouble(6, reservation.getAmount());
            statement.setInt(7, reservation.getReservationId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void deleteReservation(int reservationId) {
        String query = "DELETE FROM Reservation WHERE reservationId = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, reservationId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
