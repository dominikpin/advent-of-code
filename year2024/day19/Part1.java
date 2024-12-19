package year2024.day19;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        String[] availableTowels = myReader.nextLine().split(", ");
        myReader.nextLine();
        int counter = 0;
        while (myReader.hasNextLine()) {
            if (parseInput(myReader.nextLine(), availableTowels)) {
                counter++;
            }
        }
        myReader.close();
        System.out.println(counter);
    }

    private static boolean parseInput(String desiredTowel, String[] availableTowels) {
        ArrayList<Integer> seen = new ArrayList<>();
        Deque<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            int start = queue.poll();
            if (seen.contains(start)) {
                continue;
            }
            seen.add(start);
            if (start == desiredTowel.length()) {
                return true;
            }
            for (String availTowel : availableTowels) {
                if (start + availTowel.length() <= desiredTowel.length()
                        && desiredTowel.substring(start, start + availTowel.length()).equals(availTowel)) {
                    queue.offerFirst(start + availTowel.length());
                }
            }
        }
        return false;
    }
}