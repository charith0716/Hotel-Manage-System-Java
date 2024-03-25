package models;

public class Room {
    private int roomId;
    private int categoryId;
    private String roomNumber;
    private boolean isReserved;

    public Room(int roomId, int categoryId, String roomNumber, boolean isReserved) {
        this.roomId = roomId;
        this.categoryId = categoryId;
        this.roomNumber = roomNumber;
        this.isReserved = isReserved;
    }

    
    public Room(){
        
    }
    // Getters and Setters
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }
}
