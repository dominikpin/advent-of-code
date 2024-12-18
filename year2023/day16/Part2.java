package year2023.day16;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Part2 {

    private static final Map<Character, int[][]> MAP = new HashMap<>();
    private static final int[][] directionMapping = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

    static {
        MAP.put('.', new int[][] { { 0, 1 }, { 2, 3 } });
        MAP.put('/', new int[][] { { 0, 2 }, { 1, 3 } });
        MAP.put('\\', new int[][] { { 0, 3 }, { 1, 2 } });
        MAP.put('-', new int[][] { { 0, 1 }, { 0, 1, 2 }, { 0, 1, 3 } });
        MAP.put('|', new int[][] { { 2, 3 }, { 0, 2, 3 }, { 1, 2, 3 } });
    }

    public Part2() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        ArrayList<String> grid = new ArrayList<>();
        AtomicInteger max = new AtomicInteger(0);
        while (myReader.hasNextLine()) {
            grid.add(myReader.nextLine());
        }
        analyzeGrid(grid, max);
        myReader.close();
        System.out.println(max);
    }

    private static AtomicInteger analyzeGrid(ArrayList<String> grid, AtomicInteger max) {
        checkDirection(grid, grid.size(), true, 0, 0, max);
        checkDirection(grid, grid.size(), true, grid.get(0).length() - 1, 1, max);
        checkDirection(grid, grid.get(0).length() - 1, false, 0, 2, max);
        checkDirection(grid, grid.get(0).length() - 1, false, grid.size() - 1, 3, max);
        return max;
    }

    private static void checkDirection(ArrayList<String> grid, int to, boolean isToX, int toOther, int direction,
            AtomicInteger max) {
        for (int b = 0; b < to; b++) {
            Tile[][] energizedTileGrid = new Tile[grid.size()][grid.get(0).length()];
            for (int i = 0; i < energizedTileGrid.length; i++) {
                for (int j = 0; j < energizedTileGrid[i].length; j++) {
                    energizedTileGrid[i][j] = new Tile();
                }
            }
            int sum = 0;
            if (isToX) {
                energizeTile(grid, energizedTileGrid, b, toOther, direction);
            } else {
                energizeTile(grid, energizedTileGrid, toOther, b, direction);
            }
            for (int i = 0; i < energizedTileGrid.length; i++) {
                for (int j = 0; j < energizedTileGrid[0].length; j++) {
                    for (int k = 0; k < 4; k++) {
                        if (energizedTileGrid[i][j].getIsFromDirection(k)) {
                            sum++;
                            break;
                        }
                    }
                }
            }
            if (sum > max.get()) {
                max.set(sum);
            }
        }
    }

    private static void energizeTile(ArrayList<String> grid, Tile[][] energizedTileGrid, int x, int y, int direction) {
        if (x < 0 || x >= grid.size() || y < 0 || y >= grid.get(0).length()) {
            return;
        }
        char gridTile = grid.get(x).charAt(y);
        if (!energizedTileGrid[x][y].getIsFromDirection(direction)) {
            energizedTileGrid[x][y].setIsFromDirection(direction, true);
        } else {
            return;
        }
        int[][] directions = MAP.get(gridTile);
        int relevantI = -1;
        for (int i = 0; i < directions.length; i++) {
            for (int j = 0; j < directions[i].length; j++) {
                if (direction == directions[i][j]) {
                    relevantI = i;
                    break;
                }
            }
            if (relevantI != -1) {
                break;
            }
        }
        for (int i = 0; i < directions[relevantI].length; i++) {
            if (directions[relevantI][i] != direction) {
                energizeTile(grid, energizedTileGrid, x + directionMapping[directions[relevantI][i]][0],
                        y + directionMapping[directions[relevantI][i]][1],
                        directions[relevantI][i] % 2 == 0 ? directions[relevantI][i] + 1
                                : directions[relevantI][i] - 1);
            }
        }
    }
}
