package year2024.day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        ArrayList<String> map = new ArrayList<>();
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            map.add(myReader.nextLine());
        }
        myReader.close();
        System.out.println(calculateDistinctPositions(map));
    }

    private static int calculateDistinctPositions(ArrayList<String> map) {
        boolean[][] distinctPositions = new boolean[map.size()][map.get(0).length()];
        int x = 0;
        int y = 0;
        char direction = ' ';
        for (int i = 0; i < distinctPositions.length; i++) {
            for (int j = 0; j < distinctPositions[0].length; j++) {
                if (map.get(i).charAt(j) == '>' || map.get(i).charAt(j) == 'v' ||
                        map.get(i).charAt(j) == '<' || map.get(i).charAt(j) == '^') {
                    x = i;
                    y = j;
                    direction = map.get(i).charAt(j);
                    distinctPositions[i][j] = true;
                }
            }
        }
        int counter = 1;
        outer: while (true) {
            switch (direction) {
                case '>' -> {
                    if (y + 1 == distinctPositions[0].length) {
                        break outer;
                    } else if (map.get(x).charAt(y + 1) == '#') {
                        direction = 'v';
                    } else {
                        y++;
                    }
                }
                case 'v' -> {
                    if (x + 1 == distinctPositions.length) {
                        break outer;
                    } else if (map.get(x + 1).charAt(y) == '#') {
                        direction = '<';
                    } else {
                        x++;
                    }
                }
                case '<' -> {
                    if (y - 1 == -1) {
                        break outer;
                    } else if (map.get(x).charAt(y - 1) == '#') {
                        direction = '^';
                    } else {
                        y--;
                    }
                }
                case '^' -> {
                    if (x - 1 == -1) {
                        break outer;
                    } else if (map.get(x - 1).charAt(y) == '#') {
                        direction = '>';
                    } else {
                        x--;
                    }
                }
            }
            if (!distinctPositions[x][y]) {
                distinctPositions[x][y] = true;
                counter++;
            }
        }
        return counter;
    }
}