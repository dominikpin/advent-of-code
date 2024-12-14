package year2015.day19;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        while (true) {
            if (myReader.nextLine().isEmpty()) {
                break;
            }
        }
        System.out.println(fabricateMolecule(myReader.nextLine()));
        myReader.close();
    }

    private static int fabricateMolecule(String molecule) {
        int counterElements = (int) molecule.chars().filter(c -> Character.isUpperCase(c)).count();
        int RnAndArAmount = molecule.length() - molecule.replaceAll("Ar", "").length();
        int YAmount = molecule.length() - molecule.replaceAll("Y", "").length();
        return counterElements - RnAndArAmount - 2 * YAmount - 1;
    }
}