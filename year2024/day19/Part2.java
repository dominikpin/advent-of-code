package year2024.day19;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        String[] availableTowels = myReader.nextLine().split(", ");
        myReader.nextLine();
        long sum = 0;
        while (myReader.hasNextLine()) {
            sum += calculateNumberOfWays(myReader.nextLine(), availableTowels, 0, new HashMap<>());
        }
        myReader.close();
        System.out.println(sum);
    }

    private static long calculateNumberOfWays(String desiredTowel, String[] availableTowels, int start,
            HashMap<Integer, Long> seen) {
        if (start == desiredTowel.length()) {
            return 1;
        }
        if (seen.keySet().contains(start)) {
            return seen.get(start);
        }
        long sum = 0;
        for (String availTowel : availableTowels) {
            if (start + availTowel.length() <= desiredTowel.length()
                    && desiredTowel.substring(start, start + availTowel.length()).equals(availTowel)) {
                sum += calculateNumberOfWays(desiredTowel, availableTowels, start + availTowel.length(), seen);
            }
        }
        seen.put(start, sum);
        return sum;
    }
}
