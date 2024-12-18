package year2016.day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part2 {

    private static char[][] keypad = {
            { ' ', ' ', '1', ' ', ' ' },
            { ' ', '2', '3', '4', ' ' },
            { '5', '6', '7', '8', '9' },
            { ' ', 'A', 'B', 'C', ' ' },
            { ' ', ' ', 'D', ' ', ' ' }
    };

    public Part2() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        String output = "";
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            output += parseInput(myReader.nextLine(), output.isEmpty() ? '5' : output.charAt(output.length() - 1));
        }
        myReader.close();
        System.out.println(output);
    }

    private static char parseInput(String line, char lastChar) {
        char[] charArray = line.toCharArray();
        int x = -1;
        int y = -1;
        outer: for (int i = 0; i < keypad.length; i++) {
            for (int j = 0; j < keypad[0].length; j++) {
                if (keypad[i][j] == lastChar) {
                    x = i;
                    y = j;
                    break outer;
                }
            }
        }
        for (int i = 0; i < charArray.length; i++) {
            switch (charArray[i]) {
                case 'U' -> {
                    x--;
                    if (x < 0 || keypad[x][y] == ' ') {
                        x++;
                    }
                }
                case 'D' -> {
                    x++;
                    if (x > keypad.length - 1 || keypad[x][y] == ' ') {
                        x--;
                    }
                }
                case 'L' -> {
                    y--;
                    if (y < 0 || keypad[x][y] == ' ') {
                        y++;
                    }
                }
                case 'R' -> {
                    y++;
                    if (y > keypad[0].length - 1 || keypad[x][y] == ' ') {
                        y--;
                    }
                }
            }
        }
        return keypad[x][y];
    }
}