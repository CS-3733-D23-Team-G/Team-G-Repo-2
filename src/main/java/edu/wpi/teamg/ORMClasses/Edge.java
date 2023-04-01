package edu.wpi.teamg.ORMClasses;

import lombok.Getter;
import lombok.Setter;

public class Edge {

    @Getter @Setter
    private Node startNode;
    @Getter @Setter
    private Node endNode;
    @Getter @Setter
    private String edgeID = startNode.getNodeID()+"_"+endNode.getNodeID();

    public Edge() {
    }
}
