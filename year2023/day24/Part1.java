package year2023.day24;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        ArrayList<double[]> hailStones = new ArrayList<>();
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            parseInput(myReader.nextLine(), hailStones);
        }
        myReader.close();
        System.out.println(calculateAllCollisions(hailStones));
    }

    private static void parseInput(String line, ArrayList<double[]> hailStones) {
        String[] split1 = line.split(" @ ");
        double[] newHailStone = new double[6];
        for (int i = 0; i < split1.length; i++) {
            String[] split2 = split1[i].split(", ");
            for (int j = 0; j < split2.length; j++) {
                newHailStone[i * 3 + j] = (double) Long.parseLong(split2[j].trim());
            }
        }
        hailStones.add(newHailStone);
    }

    private static int calculateAllCollisions(ArrayList<double[]> hailStones) {
        long min = 200000000000000L;
        long max = 400000000000000L;
        int counter = 0;
        for (double[] hailStone1 : hailStones) {
            for (double[] hailStone2 : hailStones) {
                if (hailStone1 == hailStone2 || hailStone2[3] * hailStone1[4] - hailStone2[4] * hailStone1[3] == 0
                        || hailStone1[3] == 0) {
                    continue;
                }
                // x = t1 * xv1 + x1
                // y = t1 * yv1 + y1
                // x = t2 * xv2 + x2
                // y = t2 * yv2 + y2
                // y1 = t1 * vx1 + x1
                // y2 = t2 * vx2 + x2
                // x1 + t1 * vx1 = x2 + t2 * vx2
                // t1 = (x2 + t2 * vx2 - x1) / vx1
                // y1 + t1 * vy1 = y2 + t2 * vy2
                // y1 + ((x2 + t2 * vx2 - x1) / vx1) * vy1 = y2 + t2 * vy2
                // y1 * vx1 + x2 * vy1 + t2 * vx2 * vy1 - x1 * vy1 = y2 * vx1 + t2 * vy2 * vx1
                // t2 = (y2 * vx1 + x1 * vy1 - y1 * vx1 - x2 * vy1) / (vx2 * vy1 - vy2 * vx1)
                // t2 = ((y2 - y1) * vx1 + (x1 - x2) * vy1) / (vx2 * vy1 - vy2 * vx1)
                double t2 = ((hailStone2[1] - hailStone1[1]) * hailStone1[3]
                        + (hailStone1[0] - hailStone2[0]) * hailStone1[4])
                        / (hailStone2[3] * hailStone1[4] - hailStone2[4] * hailStone1[3]);
                double t1 = (hailStone2[0] - hailStone1[0] + t2 * hailStone2[3]) / hailStone1[3];
                double x = t1 * hailStone1[3] + hailStone1[0];
                double y = t1 * hailStone1[4] + hailStone1[1];
                if (t1 > 0 && t2 > 0 && x >= min && x <= max && y >= min && y <= max) {
                    counter++;
                }
            }
        }
        return counter / 2;
    }
}