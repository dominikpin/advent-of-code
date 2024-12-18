package year2016.day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        System.out.println(parseInput(myReader.nextLine()));
        myReader.close();
    }

    private static int parseInput(String line) {
        String[] split = line.split(", ");
        int x = 0;
        int y = 0;
        int direction = 0;
        for (int i = 0; i < split.length; i++) {
            direction = (4 + direction + (split[i].charAt(0) == 'L' ? -1 : 1)) % 4;
            int number = Integer.parseInt(split[i].substring(1, split[i].length()));
            x += (direction % 2 == 0 ? 0 : direction < 2 ? 1 : -1) * number;
            y += (direction % 2 == 1 ? 0 : direction < 2 ? 1 : -1) * number;
        }
        return Math.abs(x) + Math.abs(y);
    }
}