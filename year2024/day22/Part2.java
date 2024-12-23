package year2024.day22;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        ArrayList<int[]> changes = new ArrayList<>();
        while (myReader.hasNextLong()) {
            changes.add(generateNextSecretCodeChanges(myReader.nextLong(), 2000));
        }
        myReader.close();
        System.out.println(getBestPrices(changes));
    }

    private static int[] generateNextSecretCodeChanges(long number, int generated) {
        int[] changes = new int[generated + 1];
        changes[0] = (int) (number % 10);
        for (int i = 0; i < generated; i++) {
            long originalNumber = number;
            long secretNumber = number << 6;
            number = mixAndPruneNumber(secretNumber, number);
            secretNumber = number >> 5;
            number = mixAndPruneNumber(secretNumber, number);
            secretNumber = number << 11;
            number = mixAndPruneNumber(secretNumber, number);
            changes[i + 1] = (int) (number % 10 - originalNumber % 10);
        }
        return changes;
    }

    private static long mixAndPruneNumber(long secretNumber, long number) {
        return (secretNumber ^ number) % 16777216;
    }

    private static int getBestPrices(ArrayList<int[]> changes) {
        HashMap<int[], Integer> possibleChanges = new HashMap<>();
        for (int[] change : changes) {
            for (int i = 1; i < change.length - 3; i++) {
                if (change[i + 3] > 0 && change[i] + change[i + 1] + change[i + 2] + change[i + 3] > 0) {
                    doesHashMapContainArray(possibleChanges,
                            new int[] { change[i], change[i + 1], change[i + 2], change[i + 3] });
                }
            }
        }
        // System.out.println(possibleChanges.size());
        int mostCommonChange = -1;
        ArrayList<int[]> mostCommonArray = new ArrayList<>();
        for (int[] change : possibleChanges.keySet()) {
            if (mostCommonChange == -1 || possibleChanges.get(change) > mostCommonChange) {
                mostCommonChange = possibleChanges.get(change);
                mostCommonArray.clear();
                mostCommonArray.add(change);
            } else if (mostCommonChange == possibleChanges.get(change)) {
                mostCommonArray.add(change);
            }
        }
        // System.out.println(mostCommonChange);
        // System.out.println(mostCommonArray.size());
        // for (int[] is : mostCommonArray) {
        // for (int i = 0; i < is.length; i++) {
        // System.out.print(is[i] + " ");
        // }
        // System.out.println();
        // }
        int maxPayment = 0;
        for (int[] common : mostCommonArray) {
            int payment = 0;
            for (int[] change : changes) {
                int onePayment = change[0];
                for (int i = 1; i < change.length - 3; i++) {
                    onePayment += change[i];
                    if (Arrays.equals(new int[] { change[i], change[i + 1], change[i + 2], change[i + 3] }, common)) {
                        onePayment += change[i + 1] + change[i + 2] + change[i + 3];
                        payment += onePayment;
                        break;
                    }
                }
            }
            // if (maxPayment < payment) {
            // maxPayment = payment;
            // for (int i = 0; i < common.length; i++) {
            // System.out.print(common[i] + " ");
            // }
            // System.out.println();
            // }
            maxPayment = Math.max(maxPayment, payment);
        }
        return maxPayment;
    }

    private static void doesHashMapContainArray(HashMap<int[], Integer> possibleChanges, int[] array) {
        for (int[] array1 : possibleChanges.keySet()) {
            if (Arrays.equals(array, array1)) {
                possibleChanges.put(array1, possibleChanges.get(array1) + 1);
                return;
            }
        }
        possibleChanges.put(array, 1);
    }
}