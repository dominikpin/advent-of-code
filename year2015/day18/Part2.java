package year2015.day18;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import helperFunctions.HelperFunctions;

public class Part2 {

    public Part2() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        ArrayList<String> map = new ArrayList<>();
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            map.add(myReader.nextLine());
        }
        myReader.close();
        System.out.println(calculateStateAfterNSteps(HelperFunctions.changeArrayListToBoolArray(map, '#'), 100));
    }

    private static int calculateStateAfterNSteps(boolean[][] map, int steps) {
        map[0][0] = true;
        map[map.length - 1][0] = true;
        map[0][map[0].length - 1] = true;
        map[map.length - 1][map[0].length - 1] = true;
        for (int i = 0; i < steps; i++) {
            boolean[][] newMap = new boolean[map.length][map[0].length];
            for (int x = 0; x < newMap.length; x++) {
                for (int y = 0; y < newMap[x].length; y++) {
                    newMap[x][y] = calculateNextStep(map, x, y);
                }
            }
            newMap[0][0] = true;
            newMap[newMap.length - 1][0] = true;
            newMap[0][newMap[0].length - 1] = true;
            newMap[newMap.length - 1][newMap[0].length - 1] = true;
            map = newMap;
        }
        int counter = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j]) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private static boolean calculateNextStep(boolean[][] map, int x, int y) {
        int a = x == 0 ? 0 : -1;
        int b = x == map.length - 1 ? 0 : 1;
        int c = y == 0 ? 0 : -1;
        int d = y == map[0].length - 1 ? 0 : 1;
        int counter = 0;
        for (int i = a; i <= b; i++) {
            for (int j = c; j <= d; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                counter += map[x + i][y + j] ? 1 : 0;
            }
        }
        return map[x][y] ? (counter == 2 || counter == 3) : counter == 3;
    }
}