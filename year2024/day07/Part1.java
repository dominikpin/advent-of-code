package year2024.day07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        long calibrationSum = 0;
        while (myReader.hasNextLine()) {
            calibrationSum += checkCalibrationInput(myReader.nextLine());
        }
        myReader.close();
        System.out.println(calibrationSum);
    }

    private static long checkCalibrationInput(String line) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(line);
        long[] numbers = new long[line.split(" ").length];
        for (int i = 0; matcher.find(); i++) {
            numbers[i] = Long.parseLong(matcher.group());
        }
        return recursion(numbers, 2, numbers[1]) ? numbers[0] : 0;
    }

    private static boolean recursion(long[] numbers, int start, long currentSum) {
        if (currentSum > numbers[0]) {
            return false;
        }
        if (start == numbers.length) {
            if (currentSum == numbers[0]) {
                return true;
            } else {
                return false;
            }
        }
        if (recursion(numbers, start + 1, currentSum * numbers[start])
                || recursion(numbers, start + 1, currentSum + numbers[start])) {
            return true;
        }
        return false;
    }
}