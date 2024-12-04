package year2024.day04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part1 {

    private static final char[] LETTERS = { 'X', 'M', 'A', 'S' };

    public Part1() throws FileNotFoundException {
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
        for (int i = 0; i < inputArray.length; i++) {
            for (int j = 0; j < inputArray[i].length; j++) {
                if (inputArray[i][j] == 'X') {
                    counter += findNextLetter(inputArray, i, j, 1, -1);
                }
            }
        }
        return counter;
    }

    private static int findNextLetter(char[][] inputArray, int x, int y, int letterNumber, int direction) {
        if (letterNumber == LETTERS.length) {
            return 1;
        }
        int a = x == 0 ? 0 : -1;
        int b = x == inputArray.length - 1 ? 0 : 1;
        int c = y == 0 ? 0 : -1;
        int d = y == inputArray.length - 1 ? 0 : 1;
        int counter = 0;
        for (int i = a; i <= b; i++) {
            for (int j = c; j <= d; j++) {
                if ((direction == -1 || direction == 3 * (i + 1) + (j + 2))
                        && inputArray[x + i][y + j] == LETTERS[letterNumber]) {
                    counter += findNextLetter(inputArray, x + i, y + j, letterNumber + 1, 3 * (i + 1) + (j + 2));
                }
            }
        }
        return counter;
    }
}
