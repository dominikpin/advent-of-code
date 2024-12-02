package year2015.day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {
    public Part1() throws FileNotFoundException {
        File myObj = new File("year2015\\day02\\input.txt");
        Scanner myReader = new Scanner(myObj);
        int surfaceAreaSum = 0;
        while (myReader.hasNextLine()) {
            surfaceAreaSum += calculateSurfaceArea(myReader.nextLine());
        }
        System.out.println(surfaceAreaSum);
        myReader.close();
    }

    private static int calculateSurfaceArea(String line) {
        String[] split = line.split("x");
        int[] dimensions = new int[3];
        for (int i = 0; i < dimensions.length; i++) {
            dimensions[i] = Integer.parseInt(split[i]);
        }
        int side1 = dimensions[0] * dimensions[1];
        int side2 = dimensions[1] * dimensions[2];
        int side3 = dimensions[0] * dimensions[2];

        return 2 * (side1 + side2 + side3) + Math.min(Math.min(side1, side2), side3);
    }
}
