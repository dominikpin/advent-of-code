package year2023.day02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {

    private static final String[][] possibleCubes = { { "12", "red" }, { "13", "green" }, { "14", "blue" } };

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        int sum = 0;
        int gameNum = 0;
        while (myReader.hasNextLine()) {
            gameNum++;
            String data = myReader.nextLine();
            if (analyzeOneLine(data)) {
                sum += gameNum;
            }
        }
        myReader.close();
        System.out.println(sum);
    }

    private static boolean analyzeOneLine(String line) {
        String[] sets = line.split(":")[1].split(";");
        for (int i = 0; i < sets.length; i++) {
            String[] set = sets[i].split(",");
            for (int j = 0; j < set.length; j++) {
                String[] cube = set[j].trim().split(" ");
                for (int k = 0; k < possibleCubes.length; k++) {
                    if (possibleCubes[k][1].equals(cube[1])
                            && Integer.parseInt(cube[0]) > Integer.parseInt(possibleCubes[k][0])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}