package year2015.day17;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        ArrayList<Integer> containers = new ArrayList<>();
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextInt()) {
            containers.add(myReader.nextInt());
        }
        myReader.close();
        System.out.println(calculateNumberOfWaysRecursively(containers, 0, 0, 0, 150));
    }

    private static int calculateNumberOfWaysRecursively(ArrayList<Integer> containers, int counter,
            int numberOfContainersFilled, int currentCapacity, int allCapacity) {
        if (currentCapacity == allCapacity) {
            return 1;
        }
        if (currentCapacity > allCapacity || counter == containers.size()) {
            return 0;
        }
        int sum = 0;
        for (int i = counter; i < containers.size(); i++) {
            sum += calculateNumberOfWaysRecursively(containers, i + 1, numberOfContainersFilled + 1,
                    currentCapacity + containers.get(i), allCapacity);
        }
        return sum;
    }
}