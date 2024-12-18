package year2023.day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
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
        ArrayList<String[]> memoTable = new ArrayList<>();
        while (true) {
            String[] newPlatform = platform.toArray(new String[0]);
            if (indexOfArray(memoTable, newPlatform) != -1) {
                int i = indexOfArray(memoTable, newPlatform);
                return loadOnNorthBeam(memoTable.get(i + (1000000000 - i) % (memoTable.size() - i)));
            } else {
                memoTable.add(newPlatform);
            }
            performACycle(platform);
        }
    }

    private static ArrayList<String> performACycle(ArrayList<String> platform) {
        tipPlatformInOneDirection(platform, 0, 1, true);
        tipPlatformInOneDirection(platform, 0, 1, false);
        tipPlatformInOneDirection(platform, platform.size() - 1, -1, true);
        tipPlatformInOneDirection(platform, platform.get(0).length() - 1, -1, false);
        return platform;
    }

    private static void tipPlatformInOneDirection(ArrayList<String> platform, int a, int b, boolean isSouthNorth) {
        if (isSouthNorth) {
            int[] lastStoppedRock = new int[platform.get(0).length()];
            Arrays.fill(lastStoppedRock, a);
            for (int i = a; i > -1 && i < platform.size(); i += b) {
                for (int j = 0; j > -1 && j < platform.get(0).length(); j++) {
                    if (platform.get(i).charAt(j) == '#') {
                        lastStoppedRock[j] = i + b;
                    } else if (platform.get(i).charAt(j) == 'O') {
                        if (i != lastStoppedRock[j]) {
                            StringBuilder stringBuilder1 = new StringBuilder(platform.get(lastStoppedRock[j]));
                            stringBuilder1.setCharAt(j, 'O');
                            String modifiedString1 = stringBuilder1.toString();
                            platform.remove(lastStoppedRock[j]);
                            platform.add(lastStoppedRock[j], modifiedString1);
                            StringBuilder stringBuilder2 = new StringBuilder(platform.get(i));
                            stringBuilder2.setCharAt(j, '.');
                            String modifiedString2 = stringBuilder2.toString();
                            platform.remove(i);
                            platform.add(i, modifiedString2);
                        }
                        lastStoppedRock[j] += b;
                    }
                }
            }
        } else {
            int[] lastStoppedRock = new int[platform.size()];
            Arrays.fill(lastStoppedRock, a);
            for (int i = a; i > -1 && i < platform.get(0).length(); i += b) {
                for (int j = 0; j > -1 && j < platform.size(); j++) {
                    if (platform.get(j).charAt(i) == '#') {
                        lastStoppedRock[j] = i + b;
                    } else if (platform.get(j).charAt(i) == 'O') {
                        if (i != lastStoppedRock[j]) {
                            StringBuilder stringBuilder = new StringBuilder(platform.get(j));
                            stringBuilder.setCharAt(lastStoppedRock[j], 'O');
                            stringBuilder.setCharAt(i, '.');
                            String modifiedString = stringBuilder.toString();
                            platform.remove(j);
                            platform.add(j, modifiedString);
                        }
                        lastStoppedRock[j] += b;
                    }
                }
            }
        }
    }

    private static int indexOfArray(ArrayList<String[]> list, String[] arr) {
        for (int i = 0; i < list.size(); i++) {
            if (Arrays.equals(list.get(i), arr)) {
                return i;
            }
        }
        return -1;
    }

    private static int loadOnNorthBeam(String[] platform) {
        int sum = 0;
        for (int i = 0; i < platform.length; i++) {
            for (int j = 0; j < platform[0].length(); j++) {
                if (platform[i].charAt(j) == 'O') {
                    sum += platform.length - i;
                }
            }
        }
        return sum;
    }
}
