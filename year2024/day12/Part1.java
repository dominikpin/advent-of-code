package year2024.day12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import helperFunctions.HelperFunctions;

public class Part1 {

    public Part1() throws FileNotFoundException {
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
            int counter = 0;
            for (int[] plant : group) {
                counter += plant[2];
            }
            sum += counter * group.size();
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
}