package year2015.day24;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        ArrayList<Integer> presents = new ArrayList<>();
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextInt()) {
            presents.add(myReader.nextInt());
        }
        myReader.close();
        System.out.println(calculateLeastQuantumEntanglement(presents));
    }

    private static long calculateLeastQuantumEntanglement(ArrayList<Integer> presents) {
        int sumWeights = 0;
        for (int i = 0; i < presents.size(); i++) {
            sumWeights += presents.get(i);
        }
        ArrayList<ArrayList<ArrayList<Integer>>> possiblePresentsConfigurationInOneBag = new ArrayList<>();
        possiblePresentsConfigurationInOneBag.add(new ArrayList<>());
        possiblePresentsConfigurationInOneBag.add(new ArrayList<>());
        getPossibleConfigurations(presents, new ArrayList<>(), possiblePresentsConfigurationInOneBag, 0, sumWeights / 4,
                0);
        possiblePresentsConfigurationInOneBag.get(0)
                .sort((a, b) -> Long.compare(calculateQuantumEntanglement(a), calculateQuantumEntanglement(b)));
        for (int i = 0; i < possiblePresentsConfigurationInOneBag.get(0).size(); i++) {
            ArrayList<Integer> bag1 = possiblePresentsConfigurationInOneBag.get(0).get(i);
            for (ArrayList<Integer> bag2 : possiblePresentsConfigurationInOneBag.get(1)) {
                if (Collections.disjoint(bag1, bag2)) {
                    for (ArrayList<Integer> bag3 : possiblePresentsConfigurationInOneBag.get(1)) {
                        if (Collections.disjoint(bag1, bag3) && Collections.disjoint(bag2, bag3)) {
                            return calculateQuantumEntanglement(bag1);
                        }
                    }
                }
            }
            possiblePresentsConfigurationInOneBag.get(0).remove(bag1);
            i--;
        }
        return -1;
    }

    private static void getPossibleConfigurations(ArrayList<Integer> presents, ArrayList<Integer> newConfiguration,
            ArrayList<ArrayList<ArrayList<Integer>>> possiblePresentsConfiguration, int currentWeight, int maxWeight,
            int counter) {
        if (currentWeight > maxWeight) {
            return;
        }
        if (currentWeight == maxWeight) {
            ArrayList<Integer> newConfig = new ArrayList<>(newConfiguration);
            if (possiblePresentsConfiguration.get(0).isEmpty()) {
                possiblePresentsConfiguration.get(0).add(newConfig);
            } else {
                int leastAmountOfPackages = possiblePresentsConfiguration.get(0).get(0).size();
                if (leastAmountOfPackages < newConfig.size()) {
                    possiblePresentsConfiguration.get(1).add(newConfig);
                } else if (leastAmountOfPackages == newConfig.size()) {
                    possiblePresentsConfiguration.get(0).add(newConfig);
                } else if (leastAmountOfPackages > newConfig.size()) {
                    possiblePresentsConfiguration.get(1).addAll(possiblePresentsConfiguration.get(0));
                    possiblePresentsConfiguration.get(0).clear();
                    possiblePresentsConfiguration.get(0).add(newConfig);
                }
            }
        }
        for (int i = counter; i < presents.size(); i++) {
            newConfiguration.add(presents.get(i));
            getPossibleConfigurations(presents, newConfiguration, possiblePresentsConfiguration,
                    currentWeight + presents.get(i), maxWeight, i + 1);
            newConfiguration.removeLast();
        }
    }

    private static long calculateQuantumEntanglement(ArrayList<Integer> array) {
        long quantumSum = 1;
        for (Integer presents : array) {
            quantumSum *= presents;
        }
        return quantumSum;
    }
}