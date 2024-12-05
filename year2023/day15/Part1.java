package year2023.day15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        String[] strings = myReader.nextLine().split(",");
        int sum = 0;
        for (String str : strings) {
            sum += holydayASCIIhash(str);
        }
        myReader.close();
        System.out.println(sum);
    }

    private static int holydayASCIIhash(String str) {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash + str.charAt(i)) * 17 % 256;
        }
        return hash;
    }
}
