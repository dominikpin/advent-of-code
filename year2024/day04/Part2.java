package year2024.day04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
        File myObj = new File("year2024\\day04\\input.txt");
        ArrayList<String> input = new ArrayList<>();
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            input.add(myReader.nextLine());
        }
        myReader.close();
        char[][] inputArray = changeInputToArray(input);
        System.out.println(findAllXMAS(inputArray));
    }

    private static char[][] changeInputToArray(ArrayList<String> input) {
        char[][] arrayChar = new char[input.size()][input.get(0).length()];
        for (int i = 0; i < arrayChar.length; i++) {
            arrayChar[i] = input.get(i).toCharArray();
        }
        return arrayChar;
    }

    private static int findAllXMAS(char[][] inputArray) {
        int counter = 0;
        for (int i = 1; i < inputArray.length - 1; i++) {
            for (int j = 1; j < inputArray[i].length - 1; j++) {
                if (inputArray[i][j] == 'A') {
                    if (isAPartOfXMAX(inputArray, i, j)) {
                        counter++;
                    }
                }
            }
        }
        return counter;
    }

    private static boolean isAPartOfXMAX(char[][] inputArray, int x, int y) {
        int mCounter = 0;
        int sCounter = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 || j == 0) {
                    continue;
                }
                if (inputArray[x + i][y + j] == 'M') {
                    mCounter++;
                }
                if (inputArray[x + i][y + j] == 'S') {
                    sCounter++;
                }
            }
        }
        if (mCounter == sCounter && mCounter == 2 && inputArray[x - 1][y - 1] != inputArray[x + 1][y + 1]) {
            return true;
        }
        return false;
    }
}
