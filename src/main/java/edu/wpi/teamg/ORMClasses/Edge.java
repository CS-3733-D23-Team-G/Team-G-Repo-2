package edu.wpi.teamg.ORMClasses;

public class Edge {
    private Node startNode;
    private Node endNode;
    private String edgeID = startNode.getNodeID()+"_"+endNode.getNodeID();

    Node getStartNode() {
        return startNode;
    }

    void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    Node getEndNode() {
        return endNode;
    }

    void setEndNode(Node endNode) {
        this.endNode = endNode;
    }

    String getEdgeID() {
        return edgeID;
    }

    void setEdgeID(String edgeID) {
        this.edgeID = edgeID;
    }
}
