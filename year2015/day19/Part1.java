package year2015.day19;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        ArrayList<String> replacements = new ArrayList<>();
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            replacements.add(myReader.nextLine());
            if (replacements.getLast().isEmpty()) {
                replacements.removeLast();
                break;
            }
        }
        System.out.println(calculateNumberOfDistinctMolecules(myReader.nextLine(), replacements));
        myReader.close();
    }

    private static int calculateNumberOfDistinctMolecules(String line, ArrayList<String> replacements) {
        ArrayList<String> newMolecules = new ArrayList<>();
        for (String replacement : replacements) {
            String[] split = replacement.split(" ");
            String original = split[0];
            String replace = split[2];
            Pattern pattern = Pattern.compile(original);
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                StringBuilder molecule = new StringBuilder(line);
                String newMolecule = molecule.replace(matcher.start(), matcher.end(), replace).toString();
                if (!newMolecules.contains(newMolecule)) {
                    newMolecules.add(newMolecule);
                }
            }
        }
        return newMolecules.size();
    }
}