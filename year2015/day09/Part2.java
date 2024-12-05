package year2015.day09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {
    public Part2() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        ArrayList<Location> locations = new ArrayList<>();
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            parseLine(myReader.nextLine(), locations);
        }
        myReader.close();
        System.out.println(calculateLongestPath(locations));
    }

    private static void parseLine(String line, ArrayList<Location> locations) {
        String[] split = line.split(" ");
        Location location1 = new Location(split[0]);
        if (!locations.contains(location1)) {
            locations.add(location1);
        } else {
            location1 = locations.get(locations.indexOf(location1));
        }
        Location location2 = new Location(split[2]);
        if (!locations.contains(location2)) {
            locations.add(location2);
        } else {
            location2 = locations.get(locations.indexOf(location2));
        }
        location1.addConnection(location2, Integer.parseInt(split[4]));
        location2.addConnection(location1, Integer.parseInt(split[4]));
    }

    private static int calculateLongestPath(ArrayList<Location> locations) {
        int max = 0;
        for (Location location : locations) {
            for (Location resetLocation : locations) {
                resetLocation.setVisited(false);
            }
            int localMax = findLongestPath(locations, location, 0, 0);
            if (localMax > max) {
                max = localMax;
            }
        }
        return max;
    }

    private static int findLongestPath(ArrayList<Location> locations, Location location, int currentPathLength,
            int minLength) {
        location.setVisited(true);
        for (Location nextLocation : location.getConnections().keySet()) {
            if (nextLocation.getVisited()) {
                continue;
            }
            minLength = findLongestPath(locations, nextLocation,
                    currentPathLength + location.getConnections().get(nextLocation),
                    minLength);
        }
        minLength = currentPathLength > minLength ? currentPathLength : minLength;
        location.setVisited(false);
        return minLength;
    }
}
