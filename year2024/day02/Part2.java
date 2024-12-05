package year2024.day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part2 {
    public Part2() throws FileNotFoundException {
        File inputPath = new File("year2024\\day02\\input.txt");
        Scanner myReader = new Scanner(inputPath);
        int safeReportsCounter = 0;
        while (myReader.hasNextLine()) {
            if (isReportSafe(myReader.nextLine(), -1))
                safeReportsCounter++;
        }
        myReader.close();
        System.out.println(safeReportsCounter);
    }

    private static boolean isReportSafe(String report, int indexRemove) {
        String[] stringNumbers = report.split(" ");
        int[] numbers = new int[stringNumbers.length - ((indexRemove == -1) ? 0 : 1)];
        for (int i = 0; i < stringNumbers.length; i++) {
            if (i == indexRemove) {
                continue;
            }
            numbers[i - (indexRemove != -1 ? indexRemove > i ? 0 : 1 : 0)] = Integer.parseInt(stringNumbers[i]);
        }

        for (int i = 1; i < numbers.length - 1; i++) {
            int difference1 = numbers[i - 1] - numbers[i];
            int difference2 = numbers[i] - numbers[i + 1];
            if (((difference1 & 0x80000000) >> 31) != ((difference2 & 0x80000000) >> 31)) {
                if (indexRemove == -1) {
                    boolean firstRemoved = false;
                    if (i == 1) {
                        firstRemoved = isReportSafe(report, i - 1);
                    }
                    return firstRemoved || isReportSafe(report, i) || isReportSafe(report, i + 1);
                } else {
                    return false;
                }
            }
            if (Math.abs(difference1) < 1 || Math.abs(difference1) > 3 || Math.abs(difference2) < 1
                    || Math.abs(difference2) > 3) {
                if (indexRemove == -1) {
                    boolean firstRemoved = false;
                    if (i == 1) {
                        firstRemoved = isReportSafe(report, i - 1);
                    }
                    return firstRemoved || isReportSafe(report, i) || isReportSafe(report, i + 1);
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
