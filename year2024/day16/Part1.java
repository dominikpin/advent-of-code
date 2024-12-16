package year2024.day16;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
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
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        queue.offer(new int[] { 0, startX, startY, 0 });
        while (!queue.isEmpty()) {
            int[] position = queue.poll();
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
                return memoTable[x][y][direction];
            }
            queue.add(new int[] { currentScore + 1 + (direction == 0 ? 0 : 1000), x, y + 1, 0 });
            queue.add(new int[] { currentScore + 1 + (direction == 1 ? 0 : 1000), x + 1, y, 1 });
            queue.add(new int[] { currentScore + 1 + (direction == 2 ? 0 : 1000), x, y - 1, 2 });
            queue.add(new int[] { currentScore + 1 + (direction == 3 ? 0 : 1000), x - 1, y, 3 });
        }
        return -1;
    }
}