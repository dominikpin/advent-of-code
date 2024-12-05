package year2015.day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part2 {
    public Part2() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        String input = myReader.nextLine();
        int floorNumber = 0;
        for (int i = 0; i < input.length(); i++) {
            floorNumber += input.charAt(i) == '(' ? 1 : -1;
            if (floorNumber < 0) {
                System.out.println(i + 1);
                break;
            }
        }
        myReader.close();
    }
}
