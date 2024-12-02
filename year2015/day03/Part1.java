package year2015.day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Part1 {
    public Part1() throws FileNotFoundException {
        File myObj = new File("year2015\\day03\\input.txt");
        Scanner myReader = new Scanner(myObj);
        System.out.println(calculatePresentsDelivered(myReader.nextLine()));
        myReader.close();
    }

    private static int calculatePresentsDelivered(String line) {
        ArrayList<int[]> visitedCoordinates = new ArrayList<>();
        int x = 0;
        int y = 0;
        visitedCoordinates.add(new int[] { x, y });
        for (int i = 0; i < line.length(); i++) {
            char direction = line.charAt(i);
            switch (direction) {
                case '^':
                    x--;
                    break;
                case '>':
                    y++;
                    break;
                case 'v':
                    x++;
                    break;
                case '<':
                    y--;
                    break;
                default:
                    break;
            }
            int[] newCoordinates = new int[] { x, y };
            if (visitedCoordinates.stream().noneMatch(coord -> Arrays.equals(coord, newCoordinates))) {
                visitedCoordinates.add(newCoordinates);
            }

        }
        return visitedCoordinates.size();
    }
}
