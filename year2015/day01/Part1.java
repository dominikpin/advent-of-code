package year2015.day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File myObj = new File("year2015\\day01\\input.txt");
        Scanner myReader = new Scanner(myObj);
        String input = myReader.nextLine();
        int floorNumber = 0;
        for (int i = 0; i < input.length(); i++) {
            floorNumber += input.charAt(i) == '(' ? 1 : -1;
        }
        myReader.close();
        System.out.println(floorNumber);
    }
}