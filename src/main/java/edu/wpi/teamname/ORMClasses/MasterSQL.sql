drop table if exists proto2.MealRequest;
drop table if exists proto2.ConferenceRoomRequest;
drop table if exists proto2.Account;
drop table if exists proto2.Request;
drop table if exists proto2.Employee;
drop type if exists proto2.enum1;
drop table if exists proto2.Move;
drop table if exists proto2.Edge;
drop table if exists proto2.LocationName;
drop table if exists proto2.Node;

create table proto2.Node(
                            nodeID int primary key,
                            xcoord int,
                            ycoord int,
                            floor char(2),
                            building varchar(40)
);

create table proto2.Edge(
                            startNode int,
                            endNode int,
                            PRIMARY KEY (startNode, endNode),
                            foreign key (startNode) references proto2.node(nodeID),
                            foreign key (endNode) references proto2.node(nodeID)
);

create table proto2.LocationName(
                                    longName varchar(100) primary key,
                                    shortName varchar(40),
                                    nodeType char(4)
);

create table proto2.Move(
                            nodeID int,
                            longName varchar(100),
                            date date,
                            PRIMARY KEY (nodeID, longName, date),
                            foreign key (nodeID) references proto2.Node(nodeID),
                            foreign key (longName) references proto2.LocationName(longName)
);

create type proto2.enum1 as enum('blank', 'processing', 'done');

create table proto2.Employee(
                    empID int primary key,
                    firstName varchar(20),
                    lastName varchar(20),
                    email varchar(254),
                    can_serve varchar(20)
);

create table proto2.Account(
    empID int primary key,
    password varchar(100),
    is_admin boolean,
    foreign key (empID) references proto2.Employee(empID)
);

create table proto2.Request (
    reqID int primary key,
    location int,
    serv_by int,
    status proto2.enum1,
    foreign key (location) references proto2.node(nodeID),
    foreign key (serv_by) references proto2.Employee(empID)
);

create table proto2.ConferenceRoomRequest(
                               reqID int primary key,
                               meetingDate date,
                               meetingTime time,
                               purpose varchar(255),
                               foreign key (reqID) references proto2.Request(reqID)
);

create table proto2.MealRequest(
                                 reqID int primary key,
                                 recipient varchar(50),
                                 mealOrder varchar(255),
                                 note varchar(255),
                                 deliveryTime time,
                                 deliveryDate date,
                                 foreign key (reqID) references proto2.Request(reqID)

);

INSERT INTO proto2.Employee (empID, firstName, lastName, email, can_serve)
VALUES
    (1, 'John', 'Doe', 'johndoe@example.com', 'Coffee'),
    (2, 'Jane', 'Doe', 'janedoe@example.com', 'Lunch'),
    (3, 'Bob', 'Smith', 'bobsmith@example.com', 'Dinner'),
    (4, 'Alice', 'Johnson', 'alicejohnson@example.com', 'All');

-- Table: proto2.Account
INSERT INTO proto2.Account (empID, password, is_admin)
VALUES
    (1, 'password123', true),
    (2, '123password', false),
    (3, 'password456', false),
    (4, '456password', true);

-- Table: proto2.Request
INSERT INTO proto2.Request (reqID, location, serv_by, status)
VALUES
    (1, 105, 1, 'blank'),
    (2, 110, 2, 'processing'),
    (3, 115, 3, 'done'),
    (4, 120, 4, 'blank'),
    (5, 105, 1, 'blank'),
    (6, 110, 2, 'processing'),
    (7, 115, 3, 'done'),
    (8, 120, 4, 'blank');

-- Table: proto2.ConferenceRoomRequest
INSERT INTO proto2.ConferenceRoomRequest (reqID, meetingDate, meetingTime, purpose)
VALUES
    (5, '2023-04-15', '02:30', 'Team meeting'),
    (6, '2023-04-16', '14:30:00', 'Client presentation'),
    (7, '2023-04-17', '10:00:00', 'Interview'),
    (8, '2023-04-18', '15:00:00', 'Training session');

-- Table: proto2.MealRequest
INSERT INTO proto2.MealRequest (reqID, recipient, mealOrder, deliveryTime, deliveryDate, note)
VALUES
    (1, 'John Doe', 'Grilled chicken sandwich', '13:00:00', '2023-04-15', 'No onions'),
    (2, 'Jane Doe', 'Vegetarian pizza', '13:00:00', '2023-04-16', 'Extra cheese'),
    (3, 'Bob Smith', 'Fish and chips', '13:00:00', '2023-04-17', 'Tartar sauce on the side'),
    (4, 'Alice Johnson', 'Caesar salad', '13:00:00', '2023-04-19', 'No croutons');

select reqID from proto2.request order by reqid desc limit 1;

select * from proto2.request join proto2.mealrequest on proto2.request.reqid = proto2.mealrequest.reqid