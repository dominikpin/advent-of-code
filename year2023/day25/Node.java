package year2023.day25;

import java.util.ArrayList;

public class Node {

    private String name;
    private ArrayList<Node> connectedNodes = new ArrayList<>();

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addConnectedNodes(Node component) {
        this.connectedNodes.add(component);
    }

    public ArrayList<Node> getConnectedNodes() {
        return this.connectedNodes;
    }

    @Override
    public String toString() {
        return String.format("%s", name);
    }
}
