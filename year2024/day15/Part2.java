package year2024.day15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
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
        char[][] charMap = makeMap(map);
        String robotMovements = "";
        while (myReader.hasNextLine()) {
            robotMovements += myReader.nextLine();
        }
        myReader.close();
        robotMovements.replaceAll("\n", "");
        System.out.println(moveRobotAndBoxesAndCalculateGPS(charMap, robotMovements));
    }

    private static char[][] makeMap(ArrayList<String> map) {
        char[][] newMap = new char[map.size()][2 * map.get(0).length()];
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).length(); j++) {
                char char1 = ' ';
                char char2 = ' ';
                char oldChar = map.get(i).charAt(j);
                if (oldChar == 'O') {
                    char1 = '[';
                    char2 = ']';
                } else if (oldChar == '@') {
                    char1 = '@';
                    char2 = '.';
                } else {
                    char1 = oldChar;
                    char2 = oldChar;
                }
                newMap[i][2 * j] = char1;
                newMap[i][2 * j + 1] = char2;
            }
        }
        return newMap;
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
            ArrayList<int[]> movingBoxes = checkIfRobotHasSpaceToMove(map, x, y, isUpDown, move);
            if (movingBoxes == null) {
                continue;
            }
            moveRobotAndBoxes(map, x, y, isUpDown, move, movingBoxes);
            x += isUpDown ? move : 0;
            y += isUpDown ? 0 : move;
        }
        return calculateGPSOfBoxes(map);
    }

    private static ArrayList<int[]> checkIfRobotHasSpaceToMove(char[][] map, int x, int y, boolean isUpDown, int move) {
        ArrayList<int[]> movingBoxes = new ArrayList<>();
        if (!isUpDown) {
            for (int i = y + move; map[x][i] != '#'; i += move) {
                if (map[x][i] == '.') {
                    return movingBoxes;
                }
            }
            return null;
        } else {
            char nextChar = map[x + move][y];
            if (nextChar != '[' && nextChar != ']') {
                if (nextChar == '.') {
                    return movingBoxes;
                } else {
                    return null;
                }
            }
            Queue<Integer> checkIfEmpty = new LinkedList<>();
            checkIfEmpty.offer((nextChar == '[' ? 0 : -1) + y);
            ArrayList<Integer> newQueue = new ArrayList<>();
            int row = 2;
            while (!checkIfEmpty.isEmpty()) {
                int checkY = checkIfEmpty.poll();
                movingBoxes.add(new int[] { row - 1, checkY });
                for (int i = checkY; i <= checkY + 1; i++) {
                    nextChar = map[x + move * row][i];
                    if (nextChar == '#') {
                        return null;
                    } else if (nextChar == '[' || nextChar == ']') {
                        newQueue.add((nextChar == '[' ? 0 : -1) + i);
                    }
                }
                if (checkIfEmpty.isEmpty()) {
                    row++;
                    checkIfEmpty.addAll(newQueue);
                    newQueue.clear();
                }
            }
        }
        return movingBoxes;
    }

    private static void moveRobotAndBoxes(char[][] map, int x, int y, boolean isUpDown, int move,
            ArrayList<int[]> movingBoxes) {
        char lastChar = map[x][y];
        map[x][y] = '.';
        if (!isUpDown) {
            for (int j = y + move; true; j += move) {
                if (lastChar == '.' || lastChar == '#') {
                    break;
                }
                char currChar = map[x][j];
                map[x][j] = lastChar;
                lastChar = currChar;
            }
        } else {
            for (int[] movingBox : movingBoxes.reversed()) {
                int row = movingBox[0];
                int column = movingBox[1];
                map[x + row * move][column] = '.';
                map[x + row * move][column + 1] = '.';
                map[x + (row + 1) * move][column] = '[';
                map[x + (row + 1) * move][column + 1] = ']';
            }
            map[x + move][y] = lastChar;
        }
    }

    private static int calculateGPSOfBoxes(char[][] map) {
        int sum = 0;
        for (int i = 1; i < map.length; i++) {
            for (int j = 1; j < map[i].length; j++) {
                if (map[i][j] == '[') {
                    sum += i * 100 + j;
                }
            }
        }
        return sum;
    }
}