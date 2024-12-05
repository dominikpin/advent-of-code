package year2015.day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        System.out.println(getSantaSNewPassword(myReader.nextLine()));
        myReader.close();
    }

    private static String getSantaSNewPassword(String startPassword) {
        while (!checkIfPasswordPassRequirements(startPassword)) {
            startPassword = nextPassword(startPassword);
        }
        return startPassword;
    }

    private static String nextPassword(String password) {
        String newString = "";
        for (int i = password.length() - 1; i > -1; i--) {
            if (password.charAt(i) == 'z') {
                newString += 'a';
            } else {
                newString = password.substring(0, i) + (char) (password.charAt(i) + 1) + newString;
                break;
            }
        }

        return newString;
    }

    private static boolean checkIfPasswordPassRequirements(String password) {
        char[] passwordChar = password.toCharArray();
        int doubleLetterCounter = 0;
        int lastDoubleLetter = -1;
        boolean containsIncreasingStraight = false;
        for (int i = 0; i < passwordChar.length; i++) {
            if (passwordChar[i] == 'i' || passwordChar[i] == 'o' || passwordChar[i] == 'l') {
                return false;
            }
            if (i != lastDoubleLetter && i != 0 && passwordChar[i - 1] == passwordChar[i]) {
                doubleLetterCounter++;
                lastDoubleLetter = i + 1;
            }
            if (i > 1 && passwordChar[i - 2] + 1 == passwordChar[i - 1] && passwordChar[i - 1] + 1 == passwordChar[i]) {
                containsIncreasingStraight = true;
            }
        }
        return doubleLetterCounter > 1 && containsIncreasingStraight;
    }
}