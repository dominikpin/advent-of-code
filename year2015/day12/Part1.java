package year2015.day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        System.out.println(analyzeLine(myReader.nextLine()));
        myReader.close();
    }

    private static int analyzeLine(String line) {
        Pattern pattern = Pattern.compile("-?\\d+");
        Matcher matcher = pattern.matcher(line);
        int sum = 0;
        while (matcher.find()) {
            sum += Integer.parseInt(matcher.group());
        }
        return sum;
    }
}