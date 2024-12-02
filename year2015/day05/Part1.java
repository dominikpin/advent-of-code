package year2015.day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {
    public Part1() throws FileNotFoundException {
        File myObj = new File("year2015\\day05\\input.txt");

        int counter = 0;
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            if (isNiceWord(myReader.nextLine())) {
                counter++;
            }
        }
        myReader.close();
        System.out.println(counter);
    }

    private static boolean isNiceWord(String word) {
        int vowelCounter = 0;
        boolean doubleLetterExists = false;
        String[] illegalSubstrings = { "ab", "cd", "pq", "xy" };
        for (String illegalSubstring : illegalSubstrings) {
            if (word.replace(illegalSubstring, "").length() != word.length()) {
                return false;
            }
        }
        for (int i = 0; i < word.length(); i++) {
            if (vowelCounter < 3 && !("aeiou".replace(word.charAt(i), ' ')).equals("aeiou")) {
                vowelCounter++;
            }
            if (i > 0 && !doubleLetterExists && word.charAt(i - 1) == word.charAt(i)) {
                doubleLetterExists = true;
            }
        }
        return doubleLetterExists && vowelCounter == 3;
    }
}
