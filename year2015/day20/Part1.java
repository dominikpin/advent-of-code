package year2015.day20;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        System.out.println(simulateElvesGoingToHouses(myReader.nextInt()));
        myReader.close();

    }

    private static int simulateElvesGoingToHouses(int input) {
        ArrayList<Integer> primes = new ArrayList<>();
        input /= 10;
        for (int i = 1; true; i++) {
            int numOfPresents = integerFactorization(primes, i);
            if (numOfPresents >= input) {
                return i;
            }
        }
    }

    private static int integerFactorization(ArrayList<Integer> primes, int number) {
        if (primes.isEmpty() || primes.getLast() <= number) {
            int i = primes.isEmpty() ? 2 : primes.getLast() + 1;
            int max = i + 100000;
            outer: for (; i < max; i++) {
                for (Integer prime : primes) {
                    if (i % prime == 0) {
                        continue outer;
                    }
                }
                primes.add(i);
            }
        }
        ArrayList<Integer> factors = new ArrayList<>();
        for (int i = 0; i < primes.size(); i++) {
            int prime = primes.get(i);
            if (number % prime == 0) {
                factors.add(prime);
                number /= prime;
                i = -1;
            }
            if (number < prime) {
                break;
            }
        }
        ArrayList<Integer> dividers = new ArrayList<>();
        getAllDivisors(factors, dividers, 1, 0);
        int sum = 0;
        for (int i = 0; i < dividers.size(); i++) {
            sum += dividers.get(i);
        }
        return sum;
    }

    private static void getAllDivisors(ArrayList<Integer> factors, ArrayList<Integer> dividers, int currentDivider,
            int amount) {
        if (amount == factors.size()) {
            if (!dividers.contains(currentDivider)) {
                dividers.add(currentDivider);
            }
            return;
        }
        getAllDivisors(factors, dividers, currentDivider, amount + 1);
        getAllDivisors(factors, dividers, currentDivider * factors.get(amount), amount + 1);
    }
}