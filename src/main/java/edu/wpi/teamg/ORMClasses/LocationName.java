package edu.wpi.teamg.ORMClasses;

public class LocationName {
    private String longName;
    private String shortName;
    private String nodeType;

    String getLongName() {
        return longName;
    }

    void setLongName(String longName) {
        this.longName = longName;
    }

    String getShortName() {
        return shortName;
    }

    void setShortName(String shortName) {
        this.shortName = shortName;
    }

    String getNodeType() {
        return nodeType;
    }

    void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }
}
