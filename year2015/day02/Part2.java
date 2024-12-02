package year2015.day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part2 {
    public Part2() throws FileNotFoundException {
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
        int min1 = Math.min(dimensions[0], dimensions[1]);
        int min2 = Math.min(dimensions[0], dimensions[2]);
        int min3 = Math.min(dimensions[1], dimensions[2]);
        return 2 * (min1 + (min1 != min2 ? min2 : min3)) + dimensions[0] * dimensions[1] * dimensions[2];
    }
}
