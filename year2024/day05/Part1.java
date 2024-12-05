package year2024.day05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part1 {
    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        ArrayList<Page> pages = new ArrayList<>();
        Scanner myReader = new Scanner(inputPath);
        int correctMiddlePageSum = 0;
        while (myReader.hasNextLine()) {
            correctMiddlePageSum += parseInput(myReader.nextLine(), pages);
        }
        myReader.close();
        System.out.println(correctMiddlePageSum);
    }

    private static int parseInput(String line, ArrayList<Page> pages) {
        if (line.length() == 0) {
            return 0;
        } else if (line.length() == 5) {
            String[] split = line.split("\\|");
            Page page1 = findPage(Integer.parseInt(split[0]), pages);
            Page page2 = findPage(Integer.parseInt(split[1]), pages);
            page2.addDependantPage(page1);
            return 0;
        }

        String[] split = line.split(",");
        ArrayList<Page> illegalPages = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            Page page = findPage(Integer.parseInt(split[i]), pages);
            if (illegalPages.contains(page)) {
                return 0;
            }
            illegalPages.addAll(page.getDependantPages());
        }
        return Integer.parseInt(split[split.length / 2]);
    }

    private static Page findPage(int number, ArrayList<Page> pages) {
        Page newPage = new Page(number);
        if (pages.contains(newPage)) {
            newPage = pages.get(pages.indexOf(newPage));
        } else {
            pages.add(newPage);
        }
        return newPage;
    }
}
