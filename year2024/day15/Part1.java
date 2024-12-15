package year2024.day15;

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
            if (map.getLast().isEmpty()) {
                map.removeLast();
                break;
            }
        }
        char[][] charMap = HelperFunctions.changeArrayListToCharArray(map);
        String robotMovements = "";
        while (myReader.hasNextLine()) {
            robotMovements += myReader.nextLine();
        }
        myReader.close();
        robotMovements.replaceAll("\n", "");
        System.out.println(moveRobotAndBoxesAndCalculateGPS(charMap, robotMovements));
    }

    private static int moveRobotAndBoxesAndCalculateGPS(char[][] map, String robotMovements) {
        int x = -1;
        int y = -1;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == '@') {
                    x = i;
                    y = j;
                }
            }
        }
        for (int i = 0; i < robotMovements.length(); i++) {
            int move = 0;
            boolean isUpDown = false;
            switch (robotMovements.charAt(i)) {
                case '^' -> {
                    move = -1;
                    isUpDown = true;
                }
                case '>' -> {
                    move = 1;
                    isUpDown = false;
                }
                case 'v' -> {
                    move = 1;
                    isUpDown = true;
                }
                case '<' -> {
                    move = -1;
                    isUpDown = false;
                }
            }
            if (!checkIfRobotHasSpaceToMove(map, x, y, isUpDown, move)) {
                continue;
            }
            moveRobotAndBoxes(map, x, y, isUpDown, move);
            x += isUpDown ? move : 0;
            y += isUpDown ? 0 : move;
        }
        return calculateGPSOfBoxes(map);
    }

    private static boolean checkIfRobotHasSpaceToMove(char[][] map, int x, int y, boolean isUpDown, int move) {
        for (int i = (isUpDown ? x : y) + move; map[isUpDown ? i : x][isUpDown ? y : i] != '#'; i += move) {
            if (isUpDown && map[i][y] == '.' || !isUpDown && map[x][i] == '.') {
                return true;
            }
        }
        return false;
    }

    private static void moveRobotAndBoxes(char[][] map, int x, int y, boolean isUpDown, int move) {
        char lastChar = map[x][y];
        map[x][y] = '.';
        for (int j = (isUpDown ? x : y) + move; true; j += move) {
            if (lastChar == '.') {
                break;
            }
            char currChar = isUpDown ? map[j][y] : map[x][j];
            map[isUpDown ? j : x][isUpDown ? y : j] = lastChar;
            lastChar = currChar;
        }
    }

    private static int calculateGPSOfBoxes(char[][] map) {
        int sum = 0;
        for (int i = 1; i < map.length; i++) {
            for (int j = 1; j < map[i].length; j++) {
                if (map[i][j] == 'O') {
                    sum += i * 100 + j;
                }
            }
        }
        return sum;
    }
}