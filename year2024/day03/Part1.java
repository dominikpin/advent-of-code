package year2024.day03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    public Part1() throws FileNotFoundException {
        File myObj = new File("year2024\\day03\\test1.txt");
        Scanner myReader = new Scanner(myObj);
        int mulSum = 0;
        while (myReader.hasNextLine()) {
            mulSum += parseInput(myReader.nextLine());
        }
        myReader.close();
        System.out.println(mulSum);
    }

    private static int parseInput(String line) {
        Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
        Matcher matcher = pattern.matcher(line);

        int mulSum = 0;
        while (matcher.find()) {
            int number1 = Integer.parseInt(matcher.group(1));
            int number2 = Integer.parseInt(matcher.group(2));
            mulSum += number1 * number2;
        }

        return mulSum;
    }
}
