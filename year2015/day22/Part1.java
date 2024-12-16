package year2015.day22;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Part1 {

    private static int[][] spells = {
            { 53, 1 },
            { 73, 1 },
            { 113, 6 },
            { 173, 6 },
            { 229, 5 },
    };

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        Scanner myReader = new Scanner(inputPath);
        int enemyHp = Integer.parseInt(myReader.nextLine().split(" ")[2]);
        int enemyDamage = Integer.parseInt(myReader.nextLine().split(" ")[1]);
        myReader.close();
        AtomicInteger minManaSpent = new AtomicInteger(-1);
        simulateBattleWithRecursion(enemyHp, enemyDamage, 50, 500, 0, true, new int[spells.length], minManaSpent);
        System.out.println(minManaSpent.get());
    }

    private static void simulateBattleWithRecursion(int enemyHp, int enemyDamage, int myHp, int currentMana,
            int totalManaSpent, boolean isMyTurn, int[] spellsCast, AtomicInteger minTotalManaSpent) {
        if (enemyHp <= 0) {
            if (minTotalManaSpent.get() == -1 || minTotalManaSpent.get() > totalManaSpent) {
                minTotalManaSpent.set(totalManaSpent);
            }
            return;
        }
        if (minTotalManaSpent.get() != -1 && totalManaSpent >= minTotalManaSpent.get() || myHp <= 0) {
            return;
        }
        int[] newSpellsCast = makeArrayCopy(spellsCast);
        for (int i = 0; i < newSpellsCast.length; i++) {
            if (newSpellsCast[i] != 0) {
                if (i == 3) {
                    enemyHp -= 3;
                } else if (i == 4) {
                    currentMana += 101;
                }
                newSpellsCast[i]--;
            }
        }
        if (isMyTurn) {
            for (int i = 0; i < spells.length; i++) {
                if (spells[i][0] > currentMana || newSpellsCast[i] != 0) {
                    continue;
                }
                newSpellsCast[i] = spells[i][1];
                simulateBattleWithRecursion(enemyHp - (i == 0 ? 4 : i == 1 ? 2 : 0), enemyDamage,
                        myHp + (i == 1 ? 2 : 0), currentMana - spells[i][0], totalManaSpent + spells[i][0], false,
                        newSpellsCast, minTotalManaSpent);
                newSpellsCast[i] = 0;
            }
        } else {
            int armor = newSpellsCast[2] == 0 ? 0 : 7;
            myHp -= enemyDamage - armor < 0 ? 1 : enemyDamage - armor;
            simulateBattleWithRecursion(enemyHp, enemyDamage, myHp, currentMana, totalManaSpent, true, newSpellsCast,
                    minTotalManaSpent);
        }
    }

    private static int[] makeArrayCopy(int[] array) {
        int[] newArray = new int[array.length];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }
}