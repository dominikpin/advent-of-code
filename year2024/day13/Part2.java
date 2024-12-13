package year2024.day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    public Part2() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        long[][] clawMachine = new long[3][2];
        long sum = 0;
        Scanner myReader = new Scanner(inputPath);
        for (int i = 0; myReader.hasNextLine(); i++) {
            sum += parseLineAndCalculatePrizePrice(myReader.nextLine(), clawMachine, i);
        }
        myReader.close();
        System.out.println(sum);
    }

    private static long parseLineAndCalculatePrizePrice(String line, long[][] clawMachine, int counter) {
        if (counter % 4 == 3) {
            return 0;
        }
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(line);
        for (int i = 0; matcher.find(); i++) {
            clawMachine[counter % 4][i] = Long.parseLong(matcher.group()) + (counter % 4 == 2 ? 10000000000000L : 0);
        }
        if (counter % 4 == 2) {
            long price = findPrice(clawMachine);
            return price;
        }
        return 0;
    }

    private static long findPrice(long[][] clawMachine) {
        // (px1 + qx2) + (py1 + qy2) == res1 + res2
        // px1 + qx2 = res1 ->
        // p = (res1 - qx2) / x1
        // py1 + qy2 = res2 ->
        // (res1 - qx2)y1 / x1 + qy2 = res2 ->
        // res1 * y1 - qx2 * y1 + qy2 * x1 = res2 * x1 ->
        // (y2 * x1 - x2 * y1)q = res2 * x1 - res1 * y1 ->
        // q = (res2 * x1 - res1 * y1) / (y2 * x1 - x2 * y1)
        // p = (res1 - qx2) / x1
        if ((clawMachine[2][1] * clawMachine[0][0] - clawMachine[2][0] * clawMachine[0][1])
                % (clawMachine[1][1] * clawMachine[0][0] - clawMachine[1][0] * clawMachine[0][1]) != 0) {
            return 0;
        }
        long q = (clawMachine[2][1] * clawMachine[0][0] - clawMachine[2][0] * clawMachine[0][1])
                / (clawMachine[1][1] * clawMachine[0][0] - clawMachine[1][0] * clawMachine[0][1]);
        if ((clawMachine[2][0] - q * clawMachine[1][0]) % clawMachine[0][0] != 0) {
            return 0;
        }
        long p = (clawMachine[2][0] - q * clawMachine[1][0]) / clawMachine[0][0];
        return p * 3 + q;
    }
}