package year2024.day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File myObj = new File("year2024\\day02\\input.txt");
        Scanner myReader = new Scanner(myObj);
        int safeReportsCounter = 0;
        while (myReader.hasNextLine()) {
            if (isReportSafe(myReader.nextLine()))
                safeReportsCounter++;
        }
        myReader.close();
        System.out.println(safeReportsCounter);
    }

    private static boolean isReportSafe(String report) {
        String[] stringNumbers = report.split(" ");
        int[] numbers = new int[stringNumbers.length];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(stringNumbers[i]);
        }
        int isDecreasing = numbers[0] - numbers[1] > 0 ? 1 : -1;
        for (int i = 0; i < numbers.length - 1; i++) {
            int difference = numbers[i] - numbers[i + 1];
            if (difference > 3 || difference < -3 || difference == 0
                    || (difference & 0x80000000) != (isDecreasing & 0x80000000)) {
                return false;
            }
        }
        return true;
    }
}