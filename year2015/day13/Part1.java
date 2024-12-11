package year2015.day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        ArrayList<Person> people = new ArrayList<>();
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            parseInput(people, myReader.nextLine());
        }
        myReader.close();
        System.out.println(getMaxHappiness(people, new ArrayList<>()));
    }

    private static void parseInput(ArrayList<Person> people, String line) {
        String[] split = line.split(" ");
        Person person1 = getPerson(people, split[0]);
        Person person2 = getPerson(people, split[10].substring(0, split[10].length() - 1));
        person1.addHappiness(person2, (split[2].equals("gain") ? 1 : -1) * Integer.parseInt(split[3]));
    }

    private static Person getPerson(ArrayList<Person> people, String name) {
        Person newPerson = new Person(name);
        if (people.contains(newPerson)) {
            newPerson = people.get(people.indexOf(newPerson));
        } else {
            people.add(newPerson);
        }
        return newPerson;
    }

    private static int getMaxHappiness(ArrayList<Person> people, ArrayList<Person> table) {
        if (table.size() == people.size()) {
            return calculateHappiness(table);
        }
        int max = -1;
        for (Person person : people) {
            if (table.contains(person)) {
                continue;
            }
            table.add(person);
            int happiness = getMaxHappiness(people, table);
            if (happiness > max) {
                max = happiness;
            }
            table.removeLast();
        }
        return max;
    }

    private static int calculateHappiness(ArrayList<Person> table) {
        int sum = 0;
        for (int i = 0; i < table.size(); i++) {
            Person personMain = table.get(i);
            Person personLeft = i != 0 ? table.get(i - 1) : table.get(table.size() - 1);
            Person personRight = i != table.size() - 1 ? table.get(i + 1) : table.get(0);
            sum += personMain.getHappiness(personLeft) + personMain.getHappiness(personRight);
        }
        return sum;
    }
}