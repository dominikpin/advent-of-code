package year2024.day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        System.out.println(howManyStones(myReader.nextLine(), 75));
        myReader.close();
    }

    private static long howManyStones(String line, int numberOfIterations) {
        String[] split = line.split(" ");
        long sum = 0;
        HashMap<Long, ArrayList<long[]>> memo = new HashMap<>();
        for (String string : split) {
            sum += intoHowManyStonesSplit(Long.parseLong(string), memo, 75);
        }
        return sum;
    }

    private static long intoHowManyStonesSplit(long stone, HashMap<Long, ArrayList<long[]>> memo, int iterationsLeft) {
        if (iterationsLeft == 0) {
            return 1;
        }
        if (memo.containsKey(stone)) {
            ArrayList<long[]> memoNumber = memo.get(stone);
            for (long[] iteration : memoNumber) {
                if (iteration[0] == iterationsLeft) {
                    return iteration[1];
                }
            }
        }
        long sum = 0;
        if (stone == 0) {
            sum += intoHowManyStonesSplit(1, memo, iterationsLeft - 1);
        } else if ((stone + "").length() % 2 == 0) {
            String stringStone = stone + "";
            sum += intoHowManyStonesSplit(Long.parseLong(stringStone.substring(0, stringStone.length() / 2)), memo,
                    iterationsLeft - 1);
            sum += intoHowManyStonesSplit(Long.parseLong(stringStone.substring(stringStone.length() / 2)), memo,
                    iterationsLeft - 1);
        } else {
            sum += intoHowManyStonesSplit(stone * 2024, memo, iterationsLeft - 1);
        }
        memo.putIfAbsent(stone, new ArrayList<>());
        memo.get(stone).add(new long[] { iterationsLeft, sum });
        return sum;
    }
}