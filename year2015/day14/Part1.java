package year2015.day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        int max = 0;
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            max = Math.max(parseInput(myReader.nextLine()), max);
        }
        myReader.close();
        System.out.println(max);
    }

    private static int parseInput(String line) {
        String[] split = line.split(" ");
        int speed = Integer.parseInt(split[3]);
        int time = Integer.parseInt(split[6]);
        int rest = Integer.parseInt(split[13]);
        return (2503 / (time + rest) * time + Math.min(time, 2503 % (time + rest))) * speed;
    }
}