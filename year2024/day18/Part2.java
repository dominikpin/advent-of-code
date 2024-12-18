package year2024.day18;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        boolean[][] map = new boolean[71][71];
        ArrayList<int[]> path = isThereAPathToEnd(map, 0, 0, new ArrayList<>());
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            String output = parseInput(myReader.nextLine(), map, path);
            if (!output.isEmpty()) {
                System.out.println(output);
                break;
            }
        }
        myReader.close();
    }

    private static String parseInput(String line, boolean[][] map, ArrayList<int[]> path) {
        int x = Integer.parseInt(line.split(",")[0]);
        int y = Integer.parseInt(line.split(",")[1]);
        map[x][y] = true;
        if (arrayListContains(path, new int[] { x, y })) {
            path.clear();
            ArrayList<int[]> output = isThereAPathToEnd(map, 0, 0, new ArrayList<>());
            if (output == null) {
                return line;
            }
            path.addAll(output);
        }
        return "";
    }

    private static ArrayList<int[]> isThereAPathToEnd(boolean[][] map, int x, int y, ArrayList<int[]> seen) {
        if (x < 0 || y < 0 || x >= map.length || y >= map[0].length || map[x][y] ||
                arrayListContains(seen, new int[] { x, y })) {
            return null;
        }
        seen.add(new int[] { x, y });
        if (x == map.length - 1 && y == map[0].length - 1) {
            ArrayList<int[]> path = new ArrayList<>();
            path.add(new int[] { x, y });
            return path;
        }
        for (int i = 0; i < 4; i++) {
            ArrayList<int[]> path = isThereAPathToEnd(map, x + i % 2 * (i < 2 ? 1 : -1),
                    y + (i + 1) % 2 * (i < 2 ? 1 : -1), seen);
            if (path != null) {
                path.add(new int[] { x, y });
                return path;
            }
        }
        return null;
    }

    private static boolean arrayListContains(ArrayList<int[]> seen, int[] array) {
        for (int[] array1 : seen) {
            if (Arrays.equals(array, array1)) {
                return true;
            }
        }
        return false;
    }
}