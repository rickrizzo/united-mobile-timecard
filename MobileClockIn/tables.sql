CREATE TABLE coordinates(
    coordinatesID int NOT NULL,
    uuid varchar(16) NOT NULL,
    locationName varchar(50) NOT NULL,
    coordinateWest long NOT NULL,
    coordinateEast long NOT NULL,
    coordinateNorth long NOT NULL,
    coordinateSouth long NOT NULL,
    FOREIGN KEY (locationName) REFERENCES location(locationName)
 );

CREATE TABLE device(
    deviceID int NOT NULL,
    uuid varchar(16) NOT NULL,
    employeeID int NOT NULL,
    FOREIGN KEY (employeeID) REFERENCES employee(employeeID)
);

CREATE TABLE employee(                                                    
	employeeID int NOT NULL,
    uuid varchar(16) NOT NULL,
    firstName varchar(50),
    lastName varchar(50),
    PRIMARY KEY (employeeID)
 );

CREATE TABLE location (
    locationID int NOT NULL,
    uuid varchar(16) NOT NULL,
    locationName varchar(50) NOT NULL UNIQUE
);

CREATE TABLE timeCard (
    timeCardID int NOT NULL,
    uuid varchar(50) NOT NULL,
    clockingIn boolean NOT NULL,
    locationName varchar(50) NOT NULL,
    assignmentID int NOT NULL,
    isLate boolean, 
    reason varchar(50),
    employeeID int NOT NULL,
    FOREIGN KEY (employeeID) REFERENCES employee(employeeID),
    FOREIGN KEY (locationName) REFERENCES location(locationName)
);