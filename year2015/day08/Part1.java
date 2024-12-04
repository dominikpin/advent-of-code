package year2015.day08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    public Part1() throws FileNotFoundException {
        File myObj = new File("year2015\\day08\\input.txt");
        Scanner myReader = new Scanner(myObj);
        int stringLengthDiffSum = 0;
        while (myReader.hasNextLine()) {
            stringLengthDiffSum += analyzeLine(myReader.nextLine());
        }
        myReader.close();
        System.out.println(stringLengthDiffSum);
    }

    private static int analyzeLine(String line) {
        int counter = 0;
        for (int i = 1; i < line.length() - 1; i++) {
            if (line.charAt(i) == '\\') {
                if (line.charAt(i + 1) == '\\' || line.charAt(i + 1) == '"') {
                    counter++;
                    i++;
                } else if (line.length() - i > 3) {
                    Pattern hexadecimalCharacter = Pattern.compile("x([0-9a-fA-F]{2})");
                    Matcher matcher = hexadecimalCharacter.matcher(line.substring(i, i + 5));
                    if (matcher.find()) {
                        counter += 3;
                    }
                }
            }
        }
        return counter + 2;
    }
}
