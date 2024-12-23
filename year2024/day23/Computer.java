package year2024.day23;

import java.util.ArrayList;

public class Computer {

    private String name;
    private ArrayList<Computer> connections = new ArrayList<>();

    public Computer(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addComputerConnection(Computer computer) {
        this.connections.add(computer);
    }

    public ArrayList<Computer> getConnections() {
        return connections;
    }

    public String toString() {
        return this.name;
    }

    public ArrayList<Computer> calculateBiggestCluster(ArrayList<Computer> mustInclude, ArrayList<Computer> seen) {
        mustInclude.add(this);
        if (seen.contains(this)) {
            return new ArrayList<>();
        }
        seen.add(this);
        ArrayList<Computer> biggestCluster = new ArrayList<>();
        for (Computer computer : this.connections) {
            if (computer.connections.containsAll(mustInclude)) {
                ArrayList<Computer> cluster = computer.calculateBiggestCluster(mustInclude, seen);
                cluster.add(computer);
                if (cluster.size() > biggestCluster.size()) {
                    biggestCluster.clear();
                    biggestCluster.addAll(cluster);
                }
            }
        }

        mustInclude.remove(this);
        return biggestCluster;
    }
}
