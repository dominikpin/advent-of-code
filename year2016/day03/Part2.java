package year2016.day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        int counter = 0;
        while (myReader.hasNextLine()) {
            counter += parseInput(myReader.nextLine().trim(), myReader.nextLine().trim(), myReader.nextLine().trim());
        }
        myReader.close();
        System.out.println(counter);
    }

    private static int parseInput(String line1, String line2, String line3) {
        String[] split1 = line1.replaceAll("\\s+", " ").split(" ");
        String[] split2 = line2.replaceAll("\\s+", " ").split(" ");
        String[] split3 = line3.replaceAll("\\s+", " ").split(" ");
        int counter = 0;
        for (int i = 0; i < split3.length; i++) {
            int a = Integer.parseInt(split1[i]);
            int b = Integer.parseInt(split2[i]);
            int c = Integer.parseInt(split3[i]);
            if ((a < b + c && b < a + c && c < a + b)) {
                counter++;
            }
        }
        return counter;
    }
}