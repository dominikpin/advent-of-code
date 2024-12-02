package year2015.day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Part2 {
    public Part2() throws FileNotFoundException {
        File myObj = new File("year2015\\day03\\input.txt");
        Scanner myReader = new Scanner(myObj);
        System.out.println(calculatePresentsDelivered(myReader.nextLine()));
        myReader.close();
    }

    private static int calculatePresentsDelivered(String line) {
        ArrayList<int[]> visitedCoordinates = new ArrayList<>();
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;

        visitedCoordinates.add(new int[] { x1, y1 });
        for (int i = 0; i < line.length(); i++) {
            char direction = line.charAt(i);
            if (i % 2 == 1) {
                x1 += direction == '^' ? -1 : direction == 'v' ? 1 : 0;
                y1 += direction == '<' ? -1 : direction == '>' ? 1 : 0;
                int[] newCoordinates = new int[] { x1, y1 };
                if (visitedCoordinates.stream().noneMatch(coord -> Arrays.equals(coord, newCoordinates))) {
                    visitedCoordinates.add(newCoordinates);
                }
            } else {
                x2 += direction == '^' ? -1 : direction == 'v' ? 1 : 0;
                y2 += direction == '<' ? -1 : direction == '>' ? 1 : 0;
                int[] newCoordinates = new int[] { x2, y2 };
                if (visitedCoordinates.stream().noneMatch(coord -> Arrays.equals(coord, newCoordinates))) {
                    visitedCoordinates.add(newCoordinates);
                }
            }
        }
        return visitedCoordinates.size();
    }
}
