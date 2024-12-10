package year2024.day09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        System.out.println(calculateCheckSum(myReader.nextLine()));
        myReader.close();
    }

    private static long calculateCheckSum(String line) {
        ArrayList<Integer> solution = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            for (int j = 0; j < line.charAt(i) - '0'; j++) {
                if (i % 2 == 0) {
                    solution.add((i / 2));
                } else {
                    solution.add((-1));
                }
            }
        }
        int indexFirst = 0;
        int indexLast = solution.size() - 1;
        while (indexLast > indexFirst) {
            if (solution.get(indexFirst) == -1) {
                solution.set(indexFirst, solution.get(indexLast));
                solution.set(indexLast, -1);
                indexLast--;
                while (solution.get(indexLast) == -1) {
                    indexLast--;
                }
            }
            indexFirst++;
        }
        long sum = 0;
        for (int i = 0; solution.get(i) != -1; i++) {
            sum += i * solution.get(i);
        }
        return sum;
    }
}