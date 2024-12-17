package year2015.day25;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        String split[] = myReader.nextLine().split(" ");
        int row = Integer.parseInt(split[16].substring(0, split[16].length() - 1));
        int column = Integer.parseInt(split[18].substring(0, split[18].length() - 1));
        myReader.close();
        System.out.println(generateNextCode(row, column));
    }

    private static long generateNextCode(int row, int column) {
        int lastNumber = row * column + ((column - 1) * (column - 1) + column - 1) / 2
                + ((row - 2) * (row - 2) + row - 2) / 2;
        long currentCode = 20151125;
        for (int i = 1; i < lastNumber; i++) {
            currentCode *= 252533;
            currentCode %= 33554393;
        }
        return currentCode;
    }
}