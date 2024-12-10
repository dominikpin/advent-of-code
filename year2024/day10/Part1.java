package year2024.day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
        System.out.println(findAllTrail(HelperFunctions.changeArrayListToIntArray(map)));
    }

    private static int findAllTrail(int[][] map) {
        int sum = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 0) {
                    sum += recursionTrail(map, 0, i, j, new ArrayList<>());
                }
            }
        }
        return sum;
    }

    private static int recursionTrail(int[][] map, int currentHeight, int x, int y, ArrayList<int[]> peeks) {
        if (currentHeight == 9) {
            for (int[] peek : peeks) {
                if (peek[0] == x && peek[1] == y) {
                    return 0;
                }
            }
            peeks.add(new int[] { x, y });
            return 1;
        }
        int a = x == 0 ? 0 : -1;
        int b = x == map.length - 1 ? 0 : 1;
        int c = y == 0 ? 0 : -1;
        int d = y == map.length - 1 ? 0 : 1;
        int counter = 0;
        for (int i = a; i <= b; i++) {
            if (map[x + i][y] == currentHeight + 1) {
                counter += recursionTrail(map, currentHeight + 1, x + i, y, peeks);
            }
        }
        for (int i = c; i <= d; i++) {
            if (map[x][y + i] == currentHeight + 1) {
                counter += recursionTrail(map, currentHeight + 1, x, y + i, peeks);
            }
        }
        return counter;
    }
}