package year2024.day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        ArrayList<String> map = new ArrayList<>();
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            map.add(myReader.nextLine());
        }
        myReader.close();
        System.out.println(numberOfPossibleObstructions(map));
    }

    private static int numberOfPossibleObstructions(ArrayList<String> map) {
        char[][] arrayMap = changeArrayListToArray(map);
        int x = 0;
        int y = 0;
        char direction = ' ';
        for (int i = 0; i < arrayMap.length; i++) {
            for (int j = 0; j < arrayMap[0].length; j++) {
                if (map.get(i).charAt(j) == '>' || map.get(i).charAt(j) == 'v' ||
                        map.get(i).charAt(j) == '<' || map.get(i).charAt(j) == '^') {
                    x = i;
                    y = j;
                    direction = map.get(i).charAt(j);
                }
            }
        }
        int counter = 0;
        for (int i = 0; i < arrayMap.length; i++) {
            for (int j = 0; j < arrayMap[0].length; j++) {
                if (arrayMap[i][j] == '.') {
                    arrayMap[i][j] = '#';
                    counter += checkIfGuardIsInLoop(direction, x, y, arrayMap) ? 1 : 0;
                    arrayMap[i][j] = '.';
                }
            }
        }
        return counter;
    }

    private static char[][] changeArrayListToArray(ArrayList<String> map) {
        char[][] arrayChar = new char[map.size()][map.get(0).length()];
        for (int i = 0; i < arrayChar.length; i++) {
            arrayChar[i] = map.get(i).toCharArray();
        }
        return arrayChar;
    }

    private static boolean checkIfGuardIsInLoop(char direction, int x, int y, char[][] map) {
        char[][][] distinctPositions = new char[map.length][map[0].length][4];
        outer: while (true) {
            switch (direction) {
                case '>' -> {
                    if (y + 1 == distinctPositions[0].length) {
                        break outer;
                    } else if (map[x][y + 1] == '#') {
                        direction = 'v';
                    } else {
                        y++;
                    }
                }
                case 'v' -> {
                    if (x + 1 == distinctPositions.length) {
                        break outer;
                    } else if (map[x + 1][y] == '#') {
                        direction = '<';
                    } else {
                        x++;
                    }
                }
                case '<' -> {
                    if (y - 1 == -1) {
                        break outer;
                    } else if (map[x][y - 1] == '#') {
                        direction = '^';
                    } else {
                        y--;
                    }
                }
                case '^' -> {
                    if (x - 1 == -1) {
                        break outer;
                    } else if (map[x - 1][y] == '#') {
                        direction = '>';
                    } else {
                        x--;
                    }
                }
            }
            if (checkDistinctPositions(distinctPositions, direction, x, y)) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkDistinctPositions(char[][][] distinctPositions, char direction, int x, int y) {
        for (int i = 0; i < distinctPositions[x][y].length; i++) {
            if (distinctPositions[x][y][i] == direction) {
                return true;
            } else if (distinctPositions[x][y][i] == '\u0000') {
                distinctPositions[x][y][i] = direction;
            }
        }
        return false;
    }
}