package year2024.day08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import helperFunctions.HelperFunctions;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        ArrayList<String> map = new ArrayList<>();
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            map.add(myReader.nextLine());
        }
        myReader.close();
        System.out.println(calculateUniqueAntinodeLocations(HelperFunctions.changeArrayListToArray(map)));
    }

    private static int calculateUniqueAntinodeLocations(char[][] map) {
        HashMap<Character, ArrayList<int[]>> charsLocations = new HashMap<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != '.') {
                    if (!charsLocations.containsKey(map[i][j])) {
                        charsLocations.put(map[i][j], new ArrayList<>());
                    }
                    charsLocations.get(map[i][j]).add(new int[] { i, j });
                }
            }
        }
        boolean[][] unique = new boolean[map.length][map[0].length];
        int counter = 0;
        for (char char1 : charsLocations.keySet()) {
            ArrayList<int[]> locations = charsLocations.get(char1);
            for (int i = 0; i < locations.size(); i++) {
                for (int j = 0; j < locations.size(); j++) {
                    if (i == j) {
                        continue;
                    }
                    int x = 2 * locations.get(i)[0] - locations.get(j)[0];
                    int y = 2 * locations.get(i)[1] - locations.get(j)[1];
                    if (x < 0 || y < 0 || x >= map.length || y >= map[0].length) {
                        continue;
                    }
                    if (!unique[x][y]) {
                        unique[x][y] = true;
                        counter++;
                    }
                }
            }
        }
        return counter;
    }
}