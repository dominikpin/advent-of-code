package year2015.day08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part2 {
    public Part2() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        int stringLengthDiffSum = 0;
        while (myReader.hasNextLine()) {
            stringLengthDiffSum += analyzeLine(myReader.nextLine());
        }
        myReader.close();
        System.out.println(stringLengthDiffSum);
    }

    private static int analyzeLine(String line) {
        String newLine = line.replaceAll("\\\\", "");
        newLine = newLine.replaceAll("\"", "");
        return line.length() - newLine.length() + 2;
    }
}
