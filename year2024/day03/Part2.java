package year2024.day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
    public Part2() throws FileNotFoundException {
        File inputPath = new File("year2024\\day03\\input.txt");
        Scanner myReader = new Scanner(inputPath);
        String line = "";
        while (myReader.hasNextLine()) {
            line += myReader.nextLine();
        }
        int mulSum = parseInput(line);
        myReader.close();
        System.out.println(mulSum);
    }

    private static int parseInput(String line) {
        String cleanedLine1 = line.replaceAll("don't\\(\\).*?do\\(\\)", "");
        String cleanedLine2 = cleanedLine1.replaceAll("don't\\(\\).*", "");

        Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
        Matcher matcher = pattern.matcher(cleanedLine2);
        int mulSum = 0;
        while (matcher.find()) {
            int number1 = Integer.parseInt(matcher.group(1));
            int number2 = Integer.parseInt(matcher.group(2));
            mulSum += number1 * number2;
        }

        return mulSum;
    }
}
