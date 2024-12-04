package year2015.day09;

import java.util.HashMap;

public class Location {

    private String name;
    private HashMap<Location, Integer> connections = new HashMap<>();
    private boolean isVisited;

    public Location(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addConnection(Location location, int distance) {
        connections.put(location, distance);
    }

    public HashMap<Location, Integer> getConnections() {
        return connections;
    }

    public void setVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public boolean getVisited() {
        return this.isVisited;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Location)) {
            return false;
        }
        if (((Location) obj).name.equals(this.name)) {
            return true;
        }
        return false;
    }
}
