package helperFunctions;

import java.util.ArrayList;

public class HelperFunctions {
    public static char[][] changeArrayListToCharArray(ArrayList<String> map) {
        char[][] arrayChar = new char[map.size()][map.get(0).length()];
        for (int i = 0; i < arrayChar.length; i++) {
            arrayChar[i] = map.get(i).toCharArray();
        }
        return arrayChar;
    }

    public static int[][] changeArrayListToIntArray(ArrayList<String> map) {
        int[][] arrayChar = new int[map.size()][map.get(0).length()];
        for (int i = 0; i < arrayChar.length; i++) {
            for (int j = 0; j < arrayChar[i].length; j++) {
                arrayChar[i][j] = map.get(i).charAt(j) - '0';
            }
        }
        return arrayChar;
    }
}
