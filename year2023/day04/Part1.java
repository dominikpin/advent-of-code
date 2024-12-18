package year2023.day04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        int sum = 0;
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            sum += analyzeOneLine(data);
        }
        myReader.close();
        System.out.println(sum);
    }

    private static int analyzeOneLine(String line) {
        int counter = 0;
        String[] winningAndOurNumbers = line.split(":")[1].split(" ", -1);
        int index = Arrays.asList(winningAndOurNumbers).indexOf("|");
        for (int i = 0; i < index; i++) {
            if (winningAndOurNumbers[i].equals("")) {
                continue;
            }
            if (winningAndOurNumbers[i].equals("|")) {
                break;
            }
            for (int j = index + 1; j < winningAndOurNumbers.length; j++) {
                if (winningAndOurNumbers[i].equals(winningAndOurNumbers[j])) {
                    counter++;
                }
            }
        }
        return (int) Math.pow(2, counter - 1);
    }
}