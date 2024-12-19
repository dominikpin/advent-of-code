package year2016.day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        int counter = 0;
        while (myReader.hasNextLine()) {
            counter += parseInput(myReader.nextLine().trim());
        }
        myReader.close();
        System.out.println(counter);
    }

    private static int parseInput(String line) {
        String[] split = line.replaceAll("\\s+", " ").split(" ");
        int a = Integer.parseInt(split[0]);
        int b = Integer.parseInt(split[1]);
        int c = Integer.parseInt(split[2]);
        return (a < b + c && b < a + c && c < a + b) ? 1 : 0;
    }
}