package year2023.day25;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        ArrayList<Node> nodes = new ArrayList<>();
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            makeNodesAndEdges(myReader.nextLine(), nodes);
        }
        myReader.close();
        System.out.println(findMinCut(nodes));
    }

    private static void makeNodesAndEdges(String line, ArrayList<Node> nodes) {
        String[] split1 = line.split(": ");
        Node originalNode = getNode(split1[0], nodes);
        String[] split2 = split1[1].split(" ");
        for (String string : split2) {
            Node connectedNode = getNode(string, nodes);
            originalNode.addConnectedNodes(connectedNode);
            connectedNode.addConnectedNodes(originalNode);
        }
    }

    private static Node getNode(String name, ArrayList<Node> nodes) {
        for (Node node : nodes) {
            if (node.getName().equals(name)) {
                return node;
            }
        }
        Node newNode = new Node(name);
        nodes.add(newNode);
        return newNode;
    }

    private static int findMinCut(ArrayList<Node> nodes) {
        Random random = new Random();
        int minCut = 0;
        int output = -1;
        while (minCut != 3) {
            ArrayList<Node> newNodes = makeNodesCopy(nodes);
            while (newNodes.size() != 2) {
                Node chosen1 = newNodes.get(random.nextInt(newNodes.size()));
                Node chosen2 = chosen1.getConnectedNodes().get(random.nextInt(chosen1.getConnectedNodes().size()));
                Node newNode = new Node(chosen1.getName() + chosen2.getName());
                for (int i = 0; i < chosen1.getConnectedNodes().size(); i++) {
                    Node node = chosen1.getConnectedNodes().get(i);
                    if (node.equals(chosen2)) {
                        continue;
                    }
                    node.getConnectedNodes().remove(chosen1);
                    node.addConnectedNodes(newNode);
                    newNode.addConnectedNodes(node);
                }
                for (int i = 0; i < chosen2.getConnectedNodes().size(); i++) {
                    Node node = chosen2.getConnectedNodes().get(i);
                    if (node.equals(chosen1)) {
                        continue;
                    }
                    node.getConnectedNodes().remove(chosen2);
                    node.addConnectedNodes(newNode);
                    newNode.addConnectedNodes(node);
                }
                newNodes.add(newNode);
                newNodes.remove(chosen1);
                newNodes.remove(chosen2);
            }
            minCut = newNodes.get(0).getConnectedNodes().size();
            output = newNodes.get(0).getName().length() / 3 * newNodes.get(1).getName().length() / 3;
        }
        return output;
    }

    private static ArrayList<Node> makeNodesCopy(ArrayList<Node> nodes) {
        ArrayList<Node> newNodes = new ArrayList<>();
        for (Node node : nodes) {
            Node originalNode = getNode(node.getName(), newNodes);
            for (Node connectedNode : node.getConnectedNodes()) {
                Node connNode = getNode(connectedNode.getName(), newNodes);
                originalNode.addConnectedNodes(connNode);
            }
        }
        return newNodes;
    }
}