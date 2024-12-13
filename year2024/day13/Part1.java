package year2024.day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        int[][] clawMachine = new int[3][2];
        int sum = 0;
        Scanner myReader = new Scanner(inputPath);
        for (int i = 0; myReader.hasNextLine(); i++) {
            sum += parseLineAndCalculatePrizePrice(myReader.nextLine(), clawMachine, i);
        }
        myReader.close();
        System.out.println(sum);
    }

    private static int parseLineAndCalculatePrizePrice(String line, int[][] clawMachine, int counter) {
        if (counter % 4 == 3) {
            return 0;
        }
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(line);
        for (int i = 0; matcher.find(); i++) {
            clawMachine[counter % 4][i] = Integer.parseInt(matcher.group());
        }
        if (counter % 4 == 2) {
            int price = findPrice(clawMachine);
            return price;
        }
        return 0;
    }

    private static int findPrice(int[][] clawMachine) {
        int minPrice = 401;
        for (int i = 0; i <= 100; i++) {
            for (int j = 0; j <= 100; j++) {
                int x = i * clawMachine[0][0] + j * clawMachine[1][0];
                int y = i * clawMachine[0][1] + j * clawMachine[1][1];
                if (x == clawMachine[2][0] && y == clawMachine[2][1]) {
                    minPrice = Math.min(minPrice, 3 * i + j);
                }
            }
        }
        if (minPrice == 401) {
            return 0;
        }
        return minPrice;
    }
}