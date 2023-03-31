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
