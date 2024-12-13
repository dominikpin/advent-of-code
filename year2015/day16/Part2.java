package year2015.day16;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Part2 {

    private static HashMap<String, String> MFCSAM = new HashMap<>();
    static {
        MFCSAM.put("children", "3");
        MFCSAM.put("cats", "7+");
        MFCSAM.put("samoyeds", "2");
        MFCSAM.put("pomeranians", "3-");
        MFCSAM.put("akitas", "0");
        MFCSAM.put("vizslas", "0");
        MFCSAM.put("goldfish", "5-");
        MFCSAM.put("trees", "3+");
        MFCSAM.put("cars", "2");
        MFCSAM.put("perfumes", "1");
    }

    public Part2() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            int notMinus1 = parseInput(myReader.nextLine());
            if (notMinus1 != -1) {
                System.out.println(notMinus1);
            }
        }
        myReader.close();
    }

    private static int parseInput(String line) {
        line += ",";
        String[] split = line.split(" ");
        for (int i = 0; i < 3; i++) {
            String thing = split[2 + 2 * i].substring(0, split[2 + 2 * i].length() - 1);
            int thingCount = Integer.parseInt(split[3 + 2 * i].substring(0, split[3 + 2 * i].length() - 1));
            String trueThingCount = MFCSAM.get(thing);
            if (trueThingCount.length() == 2) {
                if (trueThingCount.charAt(1) == '-' && thingCount >= trueThingCount.charAt(0) - '0') {
                    return -1;
                }
                if (trueThingCount.charAt(1) == '+' && thingCount <= trueThingCount.charAt(0) - '0') {
                    return -1;
                }
            } else if (Integer.parseInt(trueThingCount) != thingCount) {
                return -1;
            }
        }
        return Integer.parseInt(split[1].substring(0, split[1].length() - 1));
    }
}