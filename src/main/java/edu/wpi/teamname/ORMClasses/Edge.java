package edu.wpi.teamname.ORMClasses;

public class Edge {
    private Node startNode;
    private Node endNode;
    private String edgeID = startNode.getNodeID()+"_"+endNode.getNodeID();

    public Node getStartNode() {
        return startNode;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public void setEndNode(Node endNode) {
        this.endNode = endNode;
    }

    public String getEdgeID() {
        return edgeID;
    }

    public void setEdgeID(String edgeID) {
        this.edgeID = edgeID;
    }
}
