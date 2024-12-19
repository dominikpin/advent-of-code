package year2016.day04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        int id = 0;
        while (myReader.hasNextLine()) {
            id += isRealRoom(myReader.nextLine());
        }
        myReader.close();
        System.out.println(id);
    }

    private static int isRealRoom(String line) {
        String[] split = line.split("\\[");
        String name = split[0];
        String checkSum = split[1];
        ArrayList<CharTimes> chars = new ArrayList<>();
        for (int i = 0; i < name.length(); i++) {
            char char1 = name.charAt(i);
            if (Character.isAlphabetic(char1)) {
                CharTimes char2 = new CharTimes(char1);
                if (!chars.contains(char2)) {
                    chars.add(char2);
                } else {
                    chars.get(chars.indexOf(char2)).increaseByOne();
                }
            }
        }
        Collections.sort(chars, new Comparator<CharTimes>() {
            @Override
            public int compare(CharTimes char1, CharTimes char2) {
                int compare = -Integer.compare(char1.getNumberOfTimes(), char2.getNumberOfTimes());
                if (compare == 0) {
                    compare = Character.compare(char1.getChar(), char2.getChar());
                }
                return compare;
            }
        });
        for (int i = 0; i < checkSum.length() - 1; i++) {
            if (chars.get(i).getChar() != checkSum.charAt(i)) {
                return 0;
            }
        }
        int ID = Integer.parseInt(name.substring(name.length() - 3, name.length()));
        StringBuilder decryptedName = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            if (Character.isAlphabetic(name.charAt(i))) {
                decryptedName.append(Character.toChars((name.charAt(i) - 'a' + ID) % 26 + 'a'));
            } else if (name.charAt(i) == '-') {
                decryptedName.append(" ");
            }
        }
        if (decryptedName.toString().equals("northpole object storage ")) {
            return ID;
        }
        return 0;
    }
}