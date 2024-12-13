package year2015.day15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        ArrayList<int[]> ingredients = new ArrayList<>();
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            parseInput(myReader.nextLine(), ingredients);
        }
        myReader.close();
        System.out.println(findBestCookieRecursively(ingredients, 0, new int[ingredients.size()]));
    }

    private static void parseInput(String line, ArrayList<int[]> ingredients) {
        Pattern pattern = Pattern.compile("-?\\d+");
        Matcher matcher = pattern.matcher(line);
        int[] properties = new int[5];
        for (int i = 0; matcher.find(); i++) {
            properties[i] = Integer.parseInt(matcher.group());
        }
        ingredients.add(properties);
    }

    private static int findBestCookieRecursively(ArrayList<int[]> ingredients, int counter,
            int[] amountOfIngredients) {
        int amount = 0;
        for (int i = 0; i < amountOfIngredients.length; i++) {
            amount += amountOfIngredients[i];
        }
        if (counter == ingredients.size()) {
            if (amount == 100) {
                return calculateCookieScore(ingredients, amountOfIngredients);
            }
            return 0;
        }
        int max = 0;
        for (int i = 0; i <= 100 - amount; i++) {
            amountOfIngredients[counter] = i;
            max = Math.max(findBestCookieRecursively(ingredients, counter + 1, amountOfIngredients), max);
        }
        amountOfIngredients[counter] = 0;
        return max;
    }

    private static int calculateCookieScore(ArrayList<int[]> ingredients, int[] amountOfIngredients) {
        int sum = 1;
        for (int i = 0; i < ingredients.get(0).length - 1; i++) {
            int property = 0;
            for (int j = 0; j < ingredients.size(); j++) {
                property += ingredients.get(j)[i] * amountOfIngredients[j];
            }
            if (property < 0) {
                property = 0;
            }
            sum *= property;
        }
        return sum;
    }
}