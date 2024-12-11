package year2024.day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        System.out.println(howManyStones(myReader.nextLine(), 25));
        myReader.close();
    }

    private static int howManyStones(String line, int numberOfIterations) {
        String[] split = line.split(" ");
        ArrayList<Long> stones = new ArrayList<>();
        for (String string : split) {
            stones.add(Long.parseLong(string));
        }
        for (int i = 0; i < numberOfIterations; i++) {
            for (int j = stones.size() - 1; j > -1; j--) {
                long stone = stones.get(j);
                if (stone == 0) {
                    stones.set(j, 1L);
                } else if ((stone + "").length() % 2 == 0) {
                    String stringStone = stone + "";
                    stones.set(j, Long.parseLong(stringStone.substring(0, stringStone.length() / 2)));
                    stones.add(j + 1, Long.parseLong(stringStone.substring(stringStone.length() / 2)));
                } else {
                    stones.set(j, stone * 2024);
                }
            }
        }
        return stones.size();
    }
}