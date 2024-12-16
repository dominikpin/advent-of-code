package year2024.day16;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

import helperFunctions.HelperFunctions;

public class Part2 {

    public Part2() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "test2.txt");
        ArrayList<String> map = new ArrayList<>();
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            map.add(myReader.nextLine());
        }
        myReader.close();
        System.out.println(findBestPath(HelperFunctions.changeArrayListToCharArray(map)));
    }

    private static int findBestPath(char[][] map) {
        int startX = 0;
        int startY = 0;
        int endX = 0;
        int endY = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
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
        int[][][] memoTable = new int[map.length][map[0].length][4];
        Queue<int[]> queue1 = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        queue1.offer(new int[] { 0, startX, startY, 0 });
        int lastDirection = 0;
        int lastScore = 0;
        while (!queue1.isEmpty()) {
            int[] position = queue1.poll();
            int currentScore = position[0];
            int x = position[1];
            int y = position[2];
            int direction = position[3];
            if (map[x][y] != '#' && (memoTable[x][y][direction] == 0 || memoTable[x][y][direction] > currentScore)) {
                memoTable[x][y][direction] = currentScore;
            } else {
                continue;
            }
            if (x == endX && y == endY) {
                lastDirection = direction;
                lastScore = memoTable[x][y][direction];
                break;
            }
            queue1.add(new int[] { currentScore + 1 + (direction == 0 ? 0 : 1000), x, y + 1, 0 });
            queue1.add(new int[] { currentScore + 1 + (direction == 1 ? 0 : 1000), x + 1, y, 1 });
            queue1.add(new int[] { currentScore + 1 + (direction == 2 ? 0 : 1000), x, y - 1, 2 });
            queue1.add(new int[] { currentScore + 1 + (direction == 3 ? 0 : 1000), x - 1, y, 3 });
        }

        ArrayList<int[]> foundBestTiles = new ArrayList<>();
        Queue<int[]> queue2 = new LinkedList<>();
        queue2.offer(new int[] { endX, endY, lastDirection, lastScore });
        while (!queue2.isEmpty()) {
            int[] location = queue2.poll();
            if (containsArray(foundBestTiles, location)) {
                continue;
            }
            foundBestTiles.add(location);
            int x = location[0];
            int y = location[1];
            int direction = location[2];
            int score = location[3];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    int newX = x + (i % 2) * (i < 2 ? 1 : -1);
                    int newY = y + ((i + 1) % 2) * (i < 2 ? 1 : -1);
                    int newDir = (j + 2) % 4;
                    int adjacent = memoTable[newX][newY][newDir];
                    if (adjacent == 0) {
                        continue;
                    }
                    int newScore = 1 + adjacent + (newDir == direction ? 0 : 1000);
                    if (newScore == score) {
                        queue2.add(new int[] { newX, newY, newDir, adjacent });
                    }
                }
            }
        }
        ArrayList<int[]> solution = new ArrayList<>();
        outer: for (int[] array1 : foundBestTiles) {
            for (int[] array2 : solution) {
                if (array1[0] == array2[0] && array1[1] == array2[1]) {
                    continue outer;
                }
            }
            solution.add(array1);
        }
        return solution.size() + 1;
    }

    private static boolean containsArray(ArrayList<int[]> arrayList, int[] array) {
        for (int[] oldArray : arrayList) {
            if (Arrays.equals(array, oldArray)) {
                return true;
            }
        }
        return false;
    }
}