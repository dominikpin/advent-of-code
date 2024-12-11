package year2015.day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    public Part2() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        System.out.println(analyzeLine(myReader.nextLine()));
        myReader.close();
    }

    private static int analyzeLine(String line) {
        Pattern pattern1 = Pattern.compile(":\"red\"");
        StringBuilder string = new StringBuilder(line);
        Matcher matcher1 = pattern1.matcher(string);
        while (matcher1.find()) {
            int indexStart = matcher1.start();
            int counter = 0;
            while (!(string.charAt(indexStart) == '{' && counter == 0)) {
                if (string.charAt(indexStart) == '}') {
                    counter++;
                }
                if (string.charAt(indexStart) == '{') {
                    counter--;
                }
                indexStart--;
            }
            int indexEnd = matcher1.end();
            while (!(string.charAt(indexEnd) == '}' && counter == 0)) {
                if (string.charAt(indexEnd) == '{') {
                    counter++;
                }
                if (string.charAt(indexEnd) == '}') {
                    counter--;
                }
                indexEnd++;
            }
            string.delete(indexStart, indexEnd + 1);
            matcher1 = pattern1.matcher(string);
        }
        Pattern pattern2 = Pattern.compile("-?\\d+");
        Matcher matcher2 = pattern2.matcher(string);
        int sum = 0;
        while (matcher2.find()) {
            sum += Integer.parseInt(matcher2.group());
        }
        return sum;
    }
}