package year2015.day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File myObj = new File("year2015\\day10\\input.txt");
        Scanner myReader = new Scanner(myObj);
        String line = myReader.nextLine();
        myReader.close();
        System.out.println(lookAndSay(line, 50).length());
    }

    private static String lookAndSay(String startingString, int numberOfTimes) {
        int j = 0;
        while (j < numberOfTimes) {
            j++;
            int i = 0;
            String newString = "";
            char lastChar = startingString.charAt(i);
            int counter = 0;
            while (i < startingString.length()) {
                if (startingString.charAt(i) == lastChar) {
                    counter++;
                } else {
                    newString += counter + "" + lastChar;
                    lastChar = startingString.charAt(i);
                    counter = 1;
                }
                if (i + 1 == startingString.length()) {
                    newString += counter + "" + lastChar;
                }
                i++;
            }
            startingString = newString;
        }
        return startingString;
    }
}
