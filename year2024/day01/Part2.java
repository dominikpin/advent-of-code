package year2024.day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
        File myObj = new File("year2024\\day01\\input.txt");
        Scanner myReader = new Scanner(myObj);
        HashMap<Integer, Integer> leftNumbers = new HashMap<>();
        HashMap<Integer, Integer> rightNumbers = new HashMap<>();
        while (myReader.hasNextInt()) {
            int left = myReader.nextInt();
            leftNumbers.put(left, leftNumbers.containsKey(left) ? leftNumbers.get(left) + 1 : 1);
            int right = myReader.nextInt();
            rightNumbers.put(right, rightNumbers.containsKey(right) ? rightNumbers.get(right) + 1 : 1);
        }
        myReader.close();
        int similaritySum = 0;
        for (Integer number : leftNumbers.keySet()) {
            similaritySum += number * leftNumbers.get(number) * ((rightNumbers.get(number) == null) ? 0
                    : rightNumbers.get(number));
        }
        System.out.println(similaritySum);
    }
}
