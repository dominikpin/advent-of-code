package year2024.day20;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
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
        System.out.println(getFastestWayPossible(HelperFunctions.changeArrayListToCharArray(map)));
    }

    private static int getFastestWayPossible(char[][] map) {
        int[][] stepCounterArray = new int[map.length][map[0].length];
        int startX = -1;
        int startY = -1;
        int endX = -1;
        int endY = -1;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                stepCounterArray[i][j] = -1;
                if (map[i][j] == 'S') {
                    startX = i;
                    startY = j;
                }
                if (map[i][j] == 'E') {
                    endX = i;
                    endY = j;
                }
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { endX, endY, 0 });
        while (!queue.isEmpty()) {
            int[] location = queue.poll();
            int x = location[0];
            int y = location[1];
            int stepCounter = location[2];
            if (map[x][y] == '#' || stepCounterArray[x][y] != -1) {
                continue;
            }
            stepCounterArray[x][y] = stepCounter;
            queue.offer(new int[] { x + 1, y, stepCounter + 1 });
            queue.offer(new int[] { x - 1, y, stepCounter + 1 });
            queue.offer(new int[] { x, y + 1, stepCounter + 1 });
            queue.offer(new int[] { x, y - 1, stepCounter + 1 });
        }
        int counter = 0;
        int minSteps = stepCounterArray[startX][startY];
        ArrayList<int[]> seen = new ArrayList<>();
        ArrayList<int[]> cheats = new ArrayList<>();
        queue.offer(new int[] { startX, startY, 0 });
        while (!queue.isEmpty()) {
            int[] location = queue.poll();
            int x = location[0];
            int y = location[1];
            int stepCounter = location[2];
            if (stepCounterArray[x][y] == -1 || HelperFunctions.doesArrayListContainIntArray(seen, location, 2)) {
                continue;
            }
            seen.add(new int[] { x, y });
            for (int i = 0; i < 4; i++) {
                int cheatY = y + 2 * (i % 2 == 0 ? i < 2 ? 1 : -1 : 0);
                int cheatX = x + 2 * (i % 2 == 1 ? i < 2 ? 1 : -1 : 0);
                if (cheatX < 0 || cheatY < 0 || cheatX >= map.length || cheatY >= map[0].length
                        || stepCounterArray[cheatX][cheatY] == -1) {
                    continue;
                }
                int cheatSkip = stepCounterArray[cheatX][cheatY] + stepCounter + 2;
                if (cheatSkip < minSteps) {
                    if (minSteps - cheatSkip >= 100) {
                        counter++;
                    }
                    cheats.add(new int[] { x, y, i, minSteps - cheatSkip });
                }
            }
            queue.offer(new int[] { x + 1, y, stepCounter + 1 });
            queue.offer(new int[] { x - 1, y, stepCounter + 1 });
            queue.offer(new int[] { x, y + 1, stepCounter + 1 });
            queue.offer(new int[] { x, y - 1, stepCounter + 1 });

        }
        return counter;
    }
}