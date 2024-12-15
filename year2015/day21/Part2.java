package year2015.day21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {

    private static ArrayList<ArrayList<int[]>> shop = new ArrayList<>();

    static {
        ArrayList<int[]> weapons = new ArrayList<>();
        weapons.add(new int[] { 8, 4, 0 });
        weapons.add(new int[] { 10, 5, 0 });
        weapons.add(new int[] { 25, 6, 0 });
        weapons.add(new int[] { 40, 7, 0 });
        weapons.add(new int[] { 74, 8, 0 });
        shop.add(weapons);

        ArrayList<int[]> armor = new ArrayList<>();
        armor.add(new int[] { 0, 0, 0 });
        armor.add(new int[] { 13, 0, 1 });
        armor.add(new int[] { 31, 0, 2 });
        armor.add(new int[] { 53, 0, 3 });
        armor.add(new int[] { 75, 0, 4 });
        armor.add(new int[] { 102, 0, 5 });
        shop.add(armor);

        ArrayList<int[]> rings = new ArrayList<>();
        rings.add(new int[] { 0, 0, 0 });
        rings.add(new int[] { 25, 1, 0 });
        rings.add(new int[] { 50, 2, 0 });
        rings.add(new int[] { 100, 3, 0 });
        rings.add(new int[] { 20, 0, 1 });
        rings.add(new int[] { 40, 0, 2 });
        rings.add(new int[] { 80, 0, 3 });
        shop.add(rings);
    }

    public Part2() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        int enemyHp = Integer.parseInt(myReader.nextLine().split(" ")[2]);
        int enemyDamage = Integer.parseInt(myReader.nextLine().split(" ")[1]);
        int enemyArmor = Integer.parseInt(myReader.nextLine().split(" ")[1]);
        System.out.println(calculateLeastGoldSpent(enemyHp, enemyDamage, enemyArmor));
        myReader.close();
    }

    private static int calculateLeastGoldSpent(int enemyHp, int enemyDamage, int enemyArmor) {
        ArrayList<int[]> myPossibleStats = new ArrayList<>();
        getAllPossibleStats(0, 0, 0, 0, -1, myPossibleStats);
        myPossibleStats.sort((a, b) -> Integer.compare(a[0], b[0]));
        for (int[] stats : myPossibleStats.reversed()) {
            if (!simulateBattle(stats[1], stats[2], enemyDamage, enemyArmor, enemyHp)) {
                return stats[0];
            }
        }
        return -1;
    }

    private static void getAllPossibleStats(int cost, int damage, int armor, int shopCounter, int firstRing,
            ArrayList<int[]> myPossibleStats) {
        ArrayList<int[]> equipment = shop.get(shopCounter);
        for (int[] equipment1 : equipment) {
            if (shopCounter == 2 && firstRing != -1) {
                if (equipment.indexOf(equipment1) != firstRing || firstRing == 0) {
                    myPossibleStats
                            .add(new int[] { cost + equipment1[0], damage + equipment1[1], armor + equipment1[2] });
                }
                continue;
            }
            getAllPossibleStats(cost + equipment1[0], damage + equipment1[1], armor + equipment1[2],
                    (shopCounter != 2 ? 1 : 0) + shopCounter, shopCounter != 2 ? -1 : equipment.indexOf(equipment1),
                    myPossibleStats);
        }
    }

    private static boolean simulateBattle(int damage, int armor, int enemyDamage, int enemyArmor, int enemyHp) {
        int hp = 100;
        int myDamageCalculated = damage - enemyArmor < 1 ? 1 : damage - enemyArmor;
        int enemyDamageCalculated = enemyDamage - armor < 1 ? 1 : enemyDamage - armor;
        int a = (enemyHp % myDamageCalculated != 0 ? 1 : 0) + enemyHp / myDamageCalculated;
        int b = (hp % enemyDamageCalculated != 0 ? 1 : 0) + hp / enemyDamageCalculated;
        return a <= b;
    }
}