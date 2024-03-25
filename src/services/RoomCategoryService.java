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
import models.RoomCategory;
import utils.DBConnection;

public class RoomCategoryService {
    private Connection connection;

    // Constructor
    public RoomCategoryService() {
        // Set the connection using DBConnection class
        this.connection = DBConnection.getConnection();
    }

    // Add a method to set the connection
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    // Create
    public void addRoomCategory(RoomCategory roomCategory) {
        String query = "INSERT INTO RoomCategory (categoryId, categoryName, description) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, roomCategory.getCategoryId());
            statement.setString(2, roomCategory.getCategoryName());
            statement.setString(3, roomCategory.getDescription());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read
    public List<RoomCategory> getAllRoomCategories() {
        List<RoomCategory> roomCategories = new ArrayList<>();

        String query = "SELECT * FROM RoomCategory";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                RoomCategory roomCategory = new RoomCategory(
                        resultSet.getInt("categoryId"),
                        resultSet.getString("categoryName"),
                        resultSet.getString("description")
                );
                roomCategories.add(roomCategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roomCategories;
    }

    // Update
    public void updateRoomCategory(RoomCategory roomCategory) {
        String query = "UPDATE RoomCategory SET categoryName = ?, description = ? WHERE categoryId = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, roomCategory.getCategoryName());
            statement.setString(2, roomCategory.getDescription());
            statement.setInt(3, roomCategory.getCategoryId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void deleteRoomCategory(int categoryId) {
    String query = "DELETE rc, r FROM RoomCategory rc LEFT JOIN Room r ON rc.categoryId = r.categoryId WHERE rc.categoryId = ?";

    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, categoryId);

        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}
