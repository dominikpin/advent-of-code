package year2015.day06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part2 {
    public Part2() throws FileNotFoundException {
        File myObj = new File("year2015\\day06\\input.txt");
        int[][] lightGrid = new int[1000][1000];
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            turnOnOffLights(lightGrid, myReader.nextLine());
        }
        myReader.close();
        int lightCounter = 0;
        for (int i = 0; i < lightGrid.length; i++) {
            for (int j = 0; j < lightGrid[i].length; j++) {
                lightCounter += lightGrid[i][j];
            }
        }
        System.out.println(lightCounter);
    }

    private static void turnOnOffLights(int[][] lightGrid, String line) {
        String[] split = line.split(" ");
        int offset = 0;
        if (split[0].equals("turn")) {
            offset = 1;
        }
        String[] cords = split[1 + offset].split(",");
        int[] coordinates1 = { Integer.parseInt(cords[0]), Integer.parseInt(cords[1]) };
        cords = split[3 + offset].split(",");
        int[] coordinates2 = { Integer.parseInt(cords[0]), Integer.parseInt(cords[1]) };
        for (int i = coordinates1[0]; i <= coordinates2[0]; i++) {
            for (int j = coordinates1[1]; j <= coordinates2[1]; j++) {
                lightGrid[i][j] += offset == 1 ? split[1].equals("off") ? lightGrid[i][j] != 0 ? -1 : 0 : 1 : 2;
            }
        }
    }
}
