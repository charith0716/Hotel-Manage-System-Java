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
import models.Room;
import utils.DBConnection;

public class RoomService {
    private Connection connection;

    // Constructor
    public RoomService() {
        // Set the connection using DBConnection class
        this.connection = DBConnection.getConnection();
    }

    // Add a method to set the connection
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    // Create
    public void addRoom(Room room) {
        String query = "INSERT INTO Room (roomId, categoryId, roomNumber, isReserved) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, room.getRoomId());
            statement.setInt(2, room.getCategoryId());
            statement.setString(3, room.getRoomNumber());
            statement.setBoolean(4, room.isReserved());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read
    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();

        String query = "SELECT * FROM Room";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Room room = new Room(
                        resultSet.getInt("roomId"),
                        resultSet.getInt("categoryId"),
                        resultSet.getString("roomNumber"),
                        resultSet.getBoolean("isReserved")
                );
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }

    // Update
    public void updateRoom(Room room) {
        String query = "UPDATE Room SET categoryId = ?, roomNumber = ?, isReserved = ? WHERE roomId = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, room.getCategoryId());
            statement.setString(2, room.getRoomNumber());
            statement.setBoolean(3, room.isReserved());
            statement.setInt(4, room.getRoomId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void deleteRoom(int roomId) {
    // Delete reservations associated with the room first
    String deleteReservationsQuery = "DELETE FROM reservation WHERE roomId = ?";
    try (PreparedStatement reservationStatement = connection.prepareStatement(deleteReservationsQuery)) {
        reservationStatement.setInt(1, roomId);
        reservationStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    // Now you can delete the room
    String deleteRoomQuery = "DELETE FROM Room WHERE roomId = ?";
    try (PreparedStatement roomStatement = connection.prepareStatement(deleteRoomQuery)) {
        roomStatement.setInt(1, roomId);
        roomStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
    public List<Room> getAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();

        // Select rooms that do not have reservations
        String query = "SELECT * FROM Room r " +
                       "LEFT JOIN Reservation res ON r.roomId = res.roomId " +
                       "WHERE res.reservationId IS NULL";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Room room = new Room(
                        resultSet.getInt("roomId"),
                        resultSet.getInt("categoryId"),
                        resultSet.getString("roomNumber"),
                        resultSet.getBoolean("isReserved")
                );
                availableRooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return availableRooms;
    }


}
