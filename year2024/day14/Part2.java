package year2024.day14;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {

    public Part2() throws IOException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        ArrayList<int[]> robots = new ArrayList<>();
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            robots.add(parseInput(myReader.nextLine()));
        }
        myReader.close();
        String output = simulateAllConfigurations(robots, 101, 103);
        File file = new File(System.getProperty("user.dir"), "output.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter writer = new FileWriter(file);
        writer.write(output.toString());
        writer.close();
    }

    private static int[] parseInput(String line) {
        Pattern pattern = Pattern.compile("-?\\d+");
        Matcher matcher = pattern.matcher(line);
        int[] numbers = new int[4];

        for (int i = 0; matcher.find(); i++) {
            numbers[i] = Integer.parseInt(matcher.group());
        }
        return numbers;
    }

    private static String simulateAllConfigurations(ArrayList<int[]> robots, int sizeX, int sizeY) {
        ArrayList<boolean[][]> seen = new ArrayList<>();
        StringBuilder output = new StringBuilder();
        int i = 0;
        while (true) {

            boolean[][] map = new boolean[sizeY][sizeX];
            for (int[] robot : robots) {
                int x = (robot[0] + robot[2] * i) % sizeX;
                int y = (robot[1] + robot[3] * i) % sizeY;
                x = x < 0 ? x + sizeX : x;
                y = y < 0 ? y + sizeY : y;
                map[y][x] = true;
            }
            if (containsMap(seen, map, output, i)) {
                break;
            }

            i++;
        }
        return output.toString();
    }

    private static boolean containsMap(ArrayList<boolean[][]> seen, boolean[][] map, StringBuilder output, int i) {
        for (boolean[][] seenMap : seen) {
            if (Arrays.deepEquals(seenMap, map)) {
                return true;
            }
        }
        seen.add(map);
        output.append("Iteration " + i + ":\n");
        String stringMap = boolArrayToString(map);
        output.append(boolArrayToString(map));
        if (stringMap.contains("#".repeat(31))) {
            System.out.println(i);
            return true;
        }
        output.append("\n\n\n");
        return false;
    }

    private static String boolArrayToString(boolean[][] array) {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                string.append(array[i][j] ? '#' : ' ');
            }
            string.append('\n');
        }
        string.append('\n');
        return string.toString();
    }
}