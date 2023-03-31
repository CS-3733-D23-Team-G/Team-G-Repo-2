package edu.wpi.teamg.ORMClasses;

public class Node {
    private int nodeID;
    private int xcoord;
    private int ycoord;
    private String floor;
    private String building;

    int getNodeID() {
        return nodeID;
    }

    void setNodeID(int nodeID) {
        this.nodeID = nodeID;
    }

    int getXcoord() {
        return xcoord;
    }

    void setXcoord(int xcoord) {
        this.xcoord = xcoord;
    }

    int getYcoord() {
        return ycoord;
    }

    void setYcoord(int ycoord) {
        this.ycoord = ycoord;
    }

    String getFloor() {
        return floor;
    }

    void setFloor(String floor) {
        this.floor = floor;
    }

    String getBuilding() {
        return building;
    }

    void setBuilding(String building) {
        this.building = building;
    }
}
