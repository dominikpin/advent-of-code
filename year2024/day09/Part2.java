package year2024.day09;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        System.out.println(calculateCheckSum(myReader.nextLine()));
        myReader.close();
    }

    private static BigInteger calculateCheckSum(String line) {
        ArrayList<ArrayList<Integer>> solution = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            ArrayList<Integer> newArray = new ArrayList<>();
            for (int j = 0; j < line.charAt(i) - '0'; j++) {
                newArray.add(i % 2 == 0 ? i / 2 : -1);
            }
            solution.add(newArray);
        }

        int maxID = (line.length() - 1) / 2;
        for (int i = solution.size() - 1; i > 0; i--) {
            ArrayList<Integer> file = solution.get(i);
            if (file.isEmpty() || file.get(0) != maxID) {
                continue;
            }
            for (int j = 0; j < solution.size(); j++) {
                if (i == j) {
                    break;
                }
                ArrayList<Integer> empty = solution.get(j);
                if (empty.size() == 0) {
                    continue;
                }
                if (empty.get(0) == -1 && empty.size() >= file.size()) {
                    solution.remove(i);
                    ArrayList<Integer> newArray = new ArrayList<>();
                    for (int k = 0; k < file.size(); k++) {
                        empty.removeFirst();
                        newArray.add(-1);
                    }
                    solution.add(i, newArray);
                    if (solution.get(j).isEmpty()) {
                        solution.remove(j);
                    }
                    solution.add(j, file);
                    break;
                }
            }
            maxID--;
        }
        BigInteger sum = BigInteger.valueOf(0);
        int index = -1;
        for (ArrayList<Integer> file : solution) {
            for (int id : file) {
                index++;
                if (id == -1) {
                    continue;
                }
                sum = sum.add(BigInteger.valueOf(index).multiply(BigInteger.valueOf(id)));
            }
        }
        return sum;
    }
}