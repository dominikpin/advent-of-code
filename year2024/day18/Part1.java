package year2024.day18;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        boolean[][] map = new boolean[71][71];
        Scanner myReader = new Scanner(inputPath);
        for (int i = 0; i < 1024; i++) {
            parseInput(myReader.nextLine(), map);
        }
        myReader.close();
        System.out.println(getShortestPath(map));
    }

    private static void parseInput(String line, boolean[][] map) {
        int x = Integer.parseInt(line.split(",")[0]);
        int y = Integer.parseInt(line.split(",")[1]);
        map[x][y] = true;
    }

    private static int getShortestPath(boolean[][] map) {
        ArrayList<int[]> seen = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { 0, 0, 0 });
        while (!queue.isEmpty()) {
            int[] currentLocation = queue.poll();
            int x = currentLocation[0];
            int y = currentLocation[1];
            int stepCounter = currentLocation[2];
            if (x < 0 || y < 0 || x >= map.length || y >= map[0].length || map[x][y] ||
                    seenContains(seen, currentLocation)) {
                continue;
            }
            if (x == map.length - 1 && y == map[0].length - 1) {
                return stepCounter;
            }
            queue.offer(new int[] { x + 1, y, stepCounter + 1 });
            queue.offer(new int[] { x - 1, y, stepCounter + 1 });
            queue.offer(new int[] { x, y + 1, stepCounter + 1 });
            queue.offer(new int[] { x, y - 1, stepCounter + 1 });
        }
        return -1;
    }

    private static boolean seenContains(ArrayList<int[]> seen, int[] array) {
        for (int[] array1 : seen) {
            if (array[0] == array1[0] && array[1] == array1[1]) {
                return true;
            }
        }
        seen.add(array);
        return false;
    }
}