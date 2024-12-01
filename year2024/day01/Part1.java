package year2024.day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File myObj = new File("year2024\\day01\\input.txt");
        Scanner myReader = new Scanner(myObj);
        ArrayList<Integer> leftNumbers = new ArrayList<>();
        ArrayList<Integer> rightNumbers = new ArrayList<>();
        while (myReader.hasNextInt()) {
            leftNumbers.add(myReader.nextInt());
            rightNumbers.add(myReader.nextInt());
        }
        myReader.close();
        leftNumbers.sort(Comparator.naturalOrder());
        rightNumbers.sort(Comparator.naturalOrder());
        int differenceSum = 0;
        for (int i = 0; i < leftNumbers.size(); i++) {
            int diff = rightNumbers.get(i) - leftNumbers.get(i);
            differenceSum += diff > 0 ? diff : -diff;
        }
        System.out.println(differenceSum);
    }
}