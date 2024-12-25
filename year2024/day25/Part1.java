package year2024.day25;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import helperFunctions.HelperFunctions;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        ArrayList<int[]> keys = new ArrayList<>();
        ArrayList<int[]> locks = new ArrayList<>();
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            ArrayList<String> input = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                input.add(myReader.nextLine());
            }
            parseInput(keys, locks, HelperFunctions.changeArrayListToBoolArray(input, '#'));
            if (myReader.hasNextLine()) {
                myReader.nextLine();
            }
        }
        myReader.close();
        System.out.println(calculateNumberFitKeyLock(keys, locks));
    }

    private static void parseInput(ArrayList<int[]> keys, ArrayList<int[]> locks, boolean[][] input) {
        int[] keyOrLock = new int[input[0].length];
        boolean isLock = input[0][0];
        outer: for (int j = 0; j < input[0].length; j++) {
            for (int i = 0; i < input.length; i++) {
                if (input[i][j] != isLock) {
                    keyOrLock[j] = isLock ? i - 1 : input.length - i - 1;
                    continue outer;
                }
            }
        }
        if (isLock) {
            locks.add(keyOrLock);
        } else {
            keys.add(keyOrLock);
        }
    }

    private static int calculateNumberFitKeyLock(ArrayList<int[]> keys, ArrayList<int[]> locks) {
        int count = 0;
        for (int[] key : keys) {
            outer: for (int[] lock : locks) {
                for (int i = 0; i < key.length; i++) {
                    if (key[i] + lock[i] > 5) {
                        continue outer;
                    }
                }
                count++;
            }
        }
        return count;
    }
}