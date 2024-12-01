package year2023.day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File myObj = new File("year2023\\day14\\input.txt");
        Scanner myReader = new Scanner(myObj);
        ArrayList<String> platform;
        int sum = 0;
        while (myReader.hasNextLine()) {
            platform = loadPlatform(myReader);
            sum += analyzePlatform(platform);
        }
        myReader.close();
        System.out.println(sum);
    }

    private static ArrayList<String> loadPlatform(Scanner myReader) {
        ArrayList<String> platform = new ArrayList<String>();
        while (myReader.hasNextLine()) {
            platform.add(myReader.nextLine());
        }
        return platform;
    }

    private static int analyzePlatform(ArrayList<String> platform) {
        int[] lastStoppedRock = new int[platform.get(0).length()];
        Arrays.fill(lastStoppedRock, -1);
        int sum = 0;
        for (int i = 0; i < platform.size(); i++) {
            for (int j = 0; j < platform.get(i).length(); j++) {
                if (platform.get(i).charAt(j) == '#') {
                    lastStoppedRock[j] = i;
                } else if (platform.get(i).charAt(j) == 'O') {
                    lastStoppedRock[j]++;
                    sum += platform.size() - lastStoppedRock[j];
                }
            }
        }
        return sum;
    }
}