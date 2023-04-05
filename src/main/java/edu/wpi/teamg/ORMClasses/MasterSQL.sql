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

create table proto2.ConferenceRoomRequest(
                               reqID int primary key,
                               location int,
                               serv_by int,
                               status proto2.enum1,
                               meeting_date date,
                               meeting_time time,
                               purpose varchar(255),
                               foreign key (location) references proto2.node(nodeID),
                               foreign key (serv_by) references proto2.Employee(empID)
);

create table proto2.MealRequest(
                                 reqID int primary key,
                                 location int,
                                 serv_by int,
                                 status proto2.enum1,
                                 recipient varchar(50),
                                 "order" varchar(255),
                                 note varchar(255),
                                 foreign key (location) references proto2.node(nodeID),
                                 foreign key (serv_by) references proto2.Employee(empID)
);




