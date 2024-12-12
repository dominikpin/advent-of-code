package year2015.day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        ArrayList<Reindeer> reindeer = new ArrayList<>();
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            reindeer.add(parseInput(myReader.nextLine()));
        }
        myReader.close();
        System.out.println(calculateWinnerPoints(reindeer));
    }

    private static Reindeer parseInput(String line) {
        String[] split = line.split(" ");
        int speed = Integer.parseInt(split[3]);
        int time = Integer.parseInt(split[6]);
        int rest = Integer.parseInt(split[13]);
        return new Reindeer(speed, time, rest);
    }

    private static int calculateWinnerPoints(ArrayList<Reindeer> reindeer) {
        int[] score = new int[reindeer.size()];
        ArrayList<Integer> maxIndex = new ArrayList<>();
        for (int i = 0; i < 2503; i++) {
            int max = 0;
            for (Reindeer reindeer1 : reindeer) {
                int distance = reindeer1.advanceOneSecond();
                if (distance == max) {
                    maxIndex.add(reindeer.indexOf(reindeer1));
                }
                if (distance > max) {
                    max = distance;
                    maxIndex.clear();
                    maxIndex.add(reindeer.indexOf(reindeer1));
                }
            }
            for (Integer index : maxIndex) {
                score[index]++;
            }
        }
        int max = 0;
        for (int i : score) {
            max = Math.max(max, i);
        }
        return max;
    }
}