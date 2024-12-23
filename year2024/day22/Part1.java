package year2024.day22;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        long sum = 0;
        while (myReader.hasNextLong()) {
            sum += generateNextSecretCode(myReader.nextLong());
        }
        myReader.close();
        System.out.println(sum);
    }

    private static long generateNextSecretCode(long number) {
        for (int i = 0; i < 2000; i++) {
            long secretNumber = number << 6;
            number = mixAndPruneNumber(secretNumber, number);
            secretNumber = number >> 5;
            number = mixAndPruneNumber(secretNumber, number);
            secretNumber = number << 11;
            number = mixAndPruneNumber(secretNumber, number);
        }
        return number;
    }

    private static long mixAndPruneNumber(long secretNumber, long number) {
        return (secretNumber ^ number) % 16777216;
    }
}