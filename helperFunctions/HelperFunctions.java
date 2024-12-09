package helperFunctions;

import java.util.ArrayList;

public class HelperFunctions {
    public static char[][] changeArrayListToArray(ArrayList<String> map) {
        char[][] arrayChar = new char[map.size()][map.get(0).length()];
        for (int i = 0; i < arrayChar.length; i++) {
            arrayChar[i] = map.get(i).toCharArray();
        }
        return arrayChar;
    }
}
