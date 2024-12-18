package year2016.day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import helperFunctions.HelperFunctions;

public class Part2 {

    public Part2() throws FileNotFoundException {
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
        ArrayList<int[]> seen = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            direction = (4 + direction + (split[i].charAt(0) == 'L' ? -1 : 1)) % 4;
            int number = Integer.parseInt(split[i].substring(1, split[i].length()));
            for (int j = 1; j <= number; j++) {
                x += (direction % 2 == 0 ? 0 : direction < 2 ? 1 : -1);
                y += (direction % 2 == 1 ? 0 : direction < 2 ? 1 : -1);
                int[] newLocation = { x, y };
                if (HelperFunctions.doesArrayListContainIntArray(seen, newLocation, 2)) {
                    return Math.abs(x) + Math.abs(y);
                }
                seen.add(newLocation);
            }
        }
        return -1;
    }
}