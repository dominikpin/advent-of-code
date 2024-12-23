package year2024.day21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {

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

    public Part2() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        long sum = 0;
        while (myReader.hasNextLine()) {
            sum += calculateButtonsInput(myReader.nextLine());
        }
        myReader.close();
        System.out.println(sum);
    }

    private static long calculateButtonsInput(String line) {
        ArrayList<long[]> seen = new ArrayList<>();
        long length = calculateStringLengthAfterNDepth(convertStringToMovementKeyPad1(line, KEYPAD1), 25, 0, seen);
        return Long.parseLong(line.substring(0, line.length() - 1)) * length;
    }

    private static String convertStringToMovementKeyPad1(String line, char[][] keypad) {
        int badX = -1;
        int badY = -1;
        for (int i = 0; i < keypad.length; i++) {
            for (int j = 0; j < keypad[i].length; j++) {
                if (keypad[i][j] == ' ') {
                    badX = i;
                    badY = j;
                }
            }
        }
        char lastChar = 'A';
        ArrayList<String> strings = new ArrayList<>();
        for (int a = 0; a < line.length(); a++) {
            strings.add(getShortestPath(keypad, lastChar, line.charAt(a), badX, badY));
            lastChar = line.charAt(a);
        }
        String output = String.join("A", strings) + "A";
        return output;
    }

    private static long calculateStringLengthAfterNDepth(String line, int maxDepth, int depth, ArrayList<long[]> seen) {
        if (depth == maxDepth) {
            return line.length();
        }
        long combinedLength = 0;
        char lastChar = 'A';
        for (int i = 0; i < line.length(); i++) {
            char char1 = line.charAt(i);
            long length = doesArrayListContainLongArray(seen, new long[] { lastChar, char1, depth }, 3);
            if (length == -1) {
                String newString = getShortestPath(KEYPAD2, lastChar, char1, 0, 0) + "A";
                length = calculateStringLengthAfterNDepth(newString, maxDepth, depth + 1, seen);
                seen.add(new long[] { lastChar, char1, depth, length });
            }
            combinedLength += length;
            lastChar = char1;
        }
        return combinedLength;
    }

    private static String getShortestPath(char[][] keypad, char char1, char char2, int badX, int badY) {
        int startX = -1;
        int startY = -1;
        int endX = -1;
        int endY = -1;
        for (int i = 0; i < keypad.length; i++) {
            for (int j = 0; j < keypad[i].length; j++) {
                if (keypad[i][j] == char1) {
                    startX = i;
                    startY = j;
                }
                if (keypad[i][j] == char2) {
                    endX = i;
                    endY = j;
                }
            }
        }
        String leftRight = endY > startY ? ">" : "<";
        String upDown = endX > startX ? "v" : "^";
        ArrayList<String> newStrings = new ArrayList<>();
        if (startX != badX || endY != badY) {
            newStrings.add(leftRight.repeat(Math.abs(endY - startY)) + upDown.repeat(Math.abs(endX - startX)));
        }
        if (startY != badY || endX != badX) {
            newStrings.add(upDown.repeat(Math.abs(endX - startX)) + leftRight.repeat(Math.abs(endY - startY)));
        }
        if (newStrings.size() > 1) {
            newStrings.remove(leftRight.equals("<") ? 1 : 0);
        }
        return newStrings.get(0);
    }

    private static long doesArrayListContainLongArray(ArrayList<long[]> arrayList, long[] array,
            int endElementExcluded) {
        outer: for (long[] array1 : arrayList) {
            for (int i = 0; i < endElementExcluded; i++) {
                if (array1[i] != array[i]) {
                    continue outer;
                }
            }
            return array1[3];
        }
        return -1;
    }
}