package year2015.day17;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        ArrayList<Integer> containers = new ArrayList<>();
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextInt()) {
            containers.add(myReader.nextInt());
        }
        myReader.close();
        System.out.println(calculateNumberOfWaysRecursively(containers, 0, 0, 0, 150)[1]);
    }

    private static int[] calculateNumberOfWaysRecursively(ArrayList<Integer> containers, int counter,
            int numberOfContainersFilled, int currentCapacity, int allCapacity) {
        if (currentCapacity == allCapacity) {
            return new int[] { numberOfContainersFilled, 1 };
        }
        if (currentCapacity > allCapacity || counter == containers.size()) {
            return new int[] { containers.size(), 0 };
        }
        int min = containers.size();
        int counterMin = 0;
        for (int i = counter; i < containers.size(); i++) {
            int[] numberContainers = calculateNumberOfWaysRecursively(containers, i + 1, numberOfContainersFilled + 1,
                    currentCapacity + containers.get(i), allCapacity);
            if (numberContainers[0] == min) {
                counterMin += numberContainers[1];
            }
            if (numberContainers[0] < min) {
                min = numberContainers[0];
                counterMin = numberContainers[1];
            }
        }
        return new int[] { min, counterMin };
    }
}