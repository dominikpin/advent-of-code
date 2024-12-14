package year2024.day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        int[] quadrants = new int[4];
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            int quadrant = parseInputAndCalculatePositionAfterNIterations(myReader.nextLine(), 101, 103, 100);
            if (quadrant != -1) {
                quadrants[quadrant]++;
            }
        }
        myReader.close();
        int sum = 1;
        for (int i = 0; i < quadrants.length; i++) {
            sum *= quadrants[i];
        }
        System.out.println(sum);
    }

    private static int parseInputAndCalculatePositionAfterNIterations(String line, int sizeX, int sizeY,
            int iterations) {
        Pattern pattern = Pattern.compile("-?\\d+");
        Matcher matcher = pattern.matcher(line);
        int[] numbers = new int[4];

        for (int i = 0; matcher.find(); i++) {
            numbers[i] = Integer.parseInt(matcher.group());
        }
        int x = (numbers[0] + numbers[2] * iterations) % sizeX;
        int y = (numbers[1] + numbers[3] * iterations) % sizeY;
        x = x < 0 ? x + sizeX : x;
        y = y < 0 ? y + sizeY : y;
        if (x == sizeX / 2 || y == sizeY / 2) {
            return -1;
        }
        return x < sizeX / 2 ? (y < sizeY / 2) ? 0 : 1 : (y < sizeY / 2) ? 2 : 3;
    }
}