package year2015.day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Part2 {
    public Part2() throws FileNotFoundException {
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
        boolean hasDoubleSubstring = false;
        boolean hasDoubleLetterOneApart = false;
        HashMap<String, Integer> wantedSubstrings = new HashMap<>();
        for (int i = 0; i < word.length() - 1; i++) {
            String substring = word.substring(i, i + 2);
            if (!hasDoubleSubstring
                    && wantedSubstrings.keySet().stream()
                            .noneMatch(wantedSubstring -> wantedSubstring.equals(substring))) {
                wantedSubstrings.put(substring, i);
            } else if (!hasDoubleSubstring && wantedSubstrings.get(substring) != i - 1) {
                hasDoubleSubstring = true;
            }

            if (!hasDoubleLetterOneApart && i + 2 < word.length() && word.charAt(i) == word.charAt(i + 2)) {
                hasDoubleLetterOneApart = true;
            }
        }
        return hasDoubleLetterOneApart && hasDoubleSubstring;
    }
}
