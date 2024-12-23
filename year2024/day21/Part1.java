package year2024.day21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part1 {

    private static final char[][] KEYPAD1 = {
            { '7', '8', '9' },
            { '4', '5', '6' },
            { '1', '2', '3' },
            { ' ', '0', 'A' },
    };

    private static final char[][] KEYPAD2 = {
            { ' ', '^', 'A' },
            { '<', 'v', '>' },
    };

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        int sum = 0;
        while (myReader.hasNextLine()) {
            sum += calculateButtonsInput(myReader.nextLine());
        }
        myReader.close();
        System.out.println(sum);
    }

    private static int calculateButtonsInput(String line) {
        String output = convertStringToMovement(line, KEYPAD1);
        for (int i = 0; i < 2; i++) {
            output = convertStringToMovement(output, KEYPAD2);
        }
        return Integer.parseInt(line.substring(0, line.length() - 1)) * output.length();
    }

    private static String convertStringToMovement(String line, char[][] keypad) {
        int badX = -1;
        int badY = -1;
        int x = -1;
        int y = -1;
        for (int i = 0; i < keypad.length; i++) {
            for (int j = 0; j < keypad[i].length; j++) {
                if (keypad[i][j] == 'A') {
                    x = i;
                    y = j;
                }
                if (keypad[i][j] == ' ') {
                    badX = i;
                    badY = j;
                }
            }
        }
        ArrayList<String> strings = new ArrayList<>();
        for (int a = 0; a < line.length(); a++) {
            int endX = -1;
            int endY = -1;
            outer: for (int i = 0; i < keypad.length; i++) {
                for (int j = 0; j < keypad[i].length; j++) {
                    if (keypad[i][j] == line.charAt(a)) {
                        endX = i;
                        endY = j;
                        break outer;
                    }
                }
            }
            String leftRight = endY > y ? ">" : "<";
            String upDown = endX > x ? "v" : "^";
            ArrayList<String> newStrings = new ArrayList<>();
            if (x != badX || endY != badY) {
                newStrings.add(leftRight.repeat(Math.abs(endY - y)) +
                        upDown.repeat(Math.abs(endX - x)));
            }
            if (y != badY || endX != badX) {
                String newString = upDown.repeat(Math.abs(endX - x)) + leftRight.repeat(Math.abs(endY - y));
                if (!newStrings.contains(newString)) {
                    newStrings.add(upDown.repeat(Math.abs(endX - x)) + leftRight.repeat(Math.abs(endY - y)));
                }
            }
            if (newStrings.size() > 1) {
                newStrings.remove(leftRight.equals("<") ? 1 : 0);
            }
            strings.add(newStrings.get(0));
            x = endX;
            y = endY;
        }
        String output = String.join("A", strings) + "A";
        return output;
    }
}