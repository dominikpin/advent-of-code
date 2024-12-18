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
        int[][] arrayInt = new int[map.size()][map.get(0).length()];
        for (int i = 0; i < arrayInt.length; i++) {
            for (int j = 0; j < arrayInt[i].length; j++) {
                arrayInt[i][j] = map.get(i).charAt(j) - '0';
            }
        }
        return arrayInt;
    }

    public static boolean[][] changeArrayListToBoolArray(ArrayList<String> map, char on) {
        boolean[][] arrayBool = new boolean[map.size()][map.get(0).length()];
        for (int i = 0; i < arrayBool.length; i++) {
            for (int j = 0; j < arrayBool[i].length; j++) {
                arrayBool[i][j] = map.get(i).charAt(j) == on;
            }
        }
        return arrayBool;
    }

    public static String charArrayToString(char[][] array) {
        String string = "";
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                string += array[i][j];
            }
            string += '\n';
        }
        return string;
    }

    public static boolean doesArrayListContainIntArray(ArrayList<int[]> arrayList, int[] array,
            int endElementExcluded) {
        outer: for (int[] array1 : arrayList) {
            for (int i = 0; i < endElementExcluded; i++) {
                if (array1[i] != array[i]) {
                    continue outer;
                }
            }
            return true;
        }
        return false;
    }
}
