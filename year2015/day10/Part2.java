package year2015.day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        String line = myReader.nextLine();
        myReader.close();
        System.out.println(lookAndSay(line, 50).length());
    }

    private static String lookAndSay(String startingString, int numberOfTimes) {
        int j = 0;
        while (j < numberOfTimes) {
            j++;
            int i = 0;
            ArrayList<String> allStrings = new ArrayList<>();
            char lastChar = startingString.charAt(i);
            int counter = 0;
            while (i < startingString.length()) {
                if (startingString.charAt(i) == lastChar) {
                    counter++;
                } else {
                    allStrings.add(counter + "" + lastChar);
                    lastChar = startingString.charAt(i);
                    counter = 1;
                }
                if (i + 1 == startingString.length()) {
                    allStrings.add(counter + "" + lastChar);
                }
                i++;
            }
            startingString = String.join("", allStrings);
        }
        return startingString;
    }
}
