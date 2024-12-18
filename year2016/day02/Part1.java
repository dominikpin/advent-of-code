package year2016.day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "test1.txt");
        String output = "";
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            output += parseInput(myReader.nextLine(), output.isEmpty() ? 5 : output.charAt(output.length() - 1) - '0');
        }
        myReader.close();
        System.out.println(output);
    }

    private static int parseInput(String line, int lastNumber) {
        char[] charArray = line.toCharArray();
        int x = (lastNumber - 1) / 3;
        int y = (lastNumber - 1) % 3;
        for (int i = 0; i < charArray.length; i++) {
            switch (charArray[i]) {
                case 'U' -> x--;
                case 'D' -> x++;
                case 'L' -> y--;
                case 'R' -> y++;
            }
            switch (x) {
                case -1 -> x++;
                case 3 -> x--;
            }
            switch (y) {
                case -1 -> y++;
                case 3 -> y--;
            }
        }
        return x * 3 + y + 1;
    }
}