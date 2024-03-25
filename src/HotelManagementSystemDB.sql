CREATE TABLE RoomCategory (
    categoryId INT PRIMARY KEY,
    categoryName VARCHAR(50) NOT NULL,
    description VARCHAR(255)
);

INSERT INTO RoomCategory (categoryId, categoryName, description)
VALUES
    (1, 'Standard', 'Basic room with essential amenities'),
    (2, 'Deluxe', 'Room with additional features for extra comfort'),
    (3, 'Suite', 'Luxurious and spacious suite with premium services');


CREATE TABLE Room (
    roomId INT PRIMARY KEY,
    categoryId INT,
    roomNumber VARCHAR(10) NOT NULL,
    isReserved BOOLEAN,
    FOREIGN KEY (categoryId) REFERENCES RoomCategory(categoryId)
);

INSERT INTO Room (roomId, categoryId, roomNumber, isReserved)
VALUES
    (101, 1, '101', false),
    (102, 1, '102', true),
    (201, 2, '201', false),
    (202, 2, '202', false),
    (301, 3, '301', true);
CREATE TABLE Customer (
    customerId INT PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE
);

INSERT INTO Customer (customerId, firstName, lastName, email)
VALUES
    (1, 'John', 'Doe', 'john.doe@example.com'),
    (2, 'Jane', 'Smith', 'jane.smith@example.com'),
    (3, 'Bob', 'Johnson', 'bob.johnson@example.com');
CREATE TABLE Reservation (
    reservationId INT PRIMARY KEY,
    roomId INT,
    customerId INT,
    checkInDate DATE,
    checkOutDate DATE,
    packageType VARCHAR(20),
    amount DOUBLE,
    FOREIGN KEY (roomId) REFERENCES Room(roomId),
    FOREIGN KEY (customerId) REFERENCES Customer(customerId)
);

INSERT INTO Reservation (reservationId, roomId, customerId, checkInDate, checkOutDate, packageType, amount)
VALUES
    (101, 101, 1, '2024-03-15', '2024-03-18', 'Full Board', 350.00),
    (102, 201, 2, '2024-03-20', '2024-03-25', 'Half Board', 450.00),
    (103, 301, 3, '2024-04-01', '2024-04-05', 'Bed and Breakfast', 550.00);


-- Create User table
CREATE TABLE User (
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Insert admin account
INSERT INTO User (email, password) VALUES ('admin@admin.com', 'admin123');
