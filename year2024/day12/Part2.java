package year2024.day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import helperFunctions.HelperFunctions;

public class Part2 {

    public Part2() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        ArrayList<String> map = new ArrayList<>();
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            map.add(myReader.nextLine());
        }
        myReader.close();
        System.out.println(findAllGroupPlantsAndCalculateCost(HelperFunctions.changeArrayListToCharArray(map)));
    }

    private static int findAllGroupPlantsAndCalculateCost(char[][] map) {
        ArrayList<ArrayList<int[]>> groups = new ArrayList<>();
        ArrayList<int[]> grouped = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (containsIntArray(grouped, new int[] { i, j })) {
                    continue;
                }

                groups.add(findAllFromGroup(map, grouped, i, j));
            }
        }
        int sum = 0;
        for (ArrayList<int[]> group : groups) {
            sum += calculateGroupCost(group) * group.size();
        }
        return sum;
    }

    private static ArrayList<int[]> findAllFromGroup(char[][] map, ArrayList<int[]> grouped, int x, int y) {
        char plantChar = map[x][y];
        ArrayList<int[]> newGroup = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { x, y });
        while (!queue.isEmpty()) {
            int[] plant = queue.poll();
            x = plant[0];
            y = plant[1];
            if (map[x][y] != plantChar || containsIntArray(newGroup, plant)) {
                continue;
            }
            grouped.add(plant);
            int counter = 0;
            if (x > 0 && map[x - 1][y] == plantChar) {
                counter++;
                queue.offer(new int[] { x - 1, y });
            }
            if (x < map.length - 1 && map[x + 1][y] == plantChar) {
                counter++;
                queue.offer(new int[] { x + 1, y });
            }
            if (y > 0 && map[x][y - 1] == plantChar) {
                counter++;
                queue.offer(new int[] { x, y - 1 });
            }
            if (y < map[0].length - 1 && map[x][y + 1] == plantChar) {
                counter++;
                queue.offer(new int[] { x, y + 1 });
            }
            newGroup.add(new int[] { x, y, 4 - counter });
        }
        return newGroup;
    }

    private static boolean containsIntArray(ArrayList<int[]> arrayList, int[] array) {
        for (int[] array1 : arrayList) {
            if (array1[0] == array[0] && array1[1] == array[1]) {
                return true;
            }
        }
        return false;
    }

    private static int calculateGroupCost(ArrayList<int[]> group) {
        int minX = group.get(0)[0];
        int maxX = group.get(0)[0];
        int minY = group.get(0)[1];
        int maxY = group.get(0)[1];
        for (int[] plant : group) {
            if (plant[0] < minX) {
                minX = plant[0];
            } else if (plant[0] > maxX) {
                maxX = plant[0];
            }
            if (plant[1] < minY) {
                minY = plant[1];
            } else if (plant[1] > maxY) {
                maxY = plant[1];
            }
        }
        int sizeX = maxX - minX + 3;
        int offsetX = minX - 1;
        int sizeY = maxY - minY + 3;
        int offsetY = minY - 1;
        boolean[][] groupMap = new boolean[sizeX][sizeY];

        for (int[] plant : group) {
            groupMap[plant[0] - offsetX][plant[1] - offsetY] = true;
        }
        int sum = 0;
        boolean edge = false;
        boolean first = false;
        for (int i = 0; i < groupMap.length - 1; i++) {
            first = false;
            for (int j = 0; j < groupMap[i].length; j++) {
                if (groupMap[i][j] != groupMap[i + 1][j] && !edge) {
                    first = groupMap[i][j];
                    edge = !edge;
                } else if (edge && groupMap[i][j] != groupMap[i + 1][j] && groupMap[i][j] != first) {
                    first = !first;
                    sum++;
                } else if (groupMap[i][j] == groupMap[i + 1][j] && edge) {
                    sum++;
                    edge = !edge;
                }
            }
        }
        for (int i = 0; i < groupMap[0].length - 1; i++) {
            first = false;
            for (int j = 0; j < groupMap.length; j++) {
                if (groupMap[j][i] != groupMap[j][i + 1] && !edge) {
                    first = groupMap[j][i];
                    edge = !edge;
                } else if (edge && groupMap[j][i] != groupMap[j][i + 1] && groupMap[j][i] != first) {
                    first = !first;
                    sum++;
                } else if (groupMap[j][i] == groupMap[j][i + 1] && edge) {
                    sum++;
                    edge = !edge;
                }
            }
        }
        return sum;
    }
}