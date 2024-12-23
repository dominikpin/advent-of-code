package year2024.day23;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        ArrayList<Computer> computers = new ArrayList<>();
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            parseInput(myReader.nextLine(), computers);
        }
        myReader.close();
        System.out.println(findBiggestInterconnectedCluster(computers));
    }

    private static void parseInput(String line, ArrayList<Computer> computers) {
        String[] split = line.split("-");
        Computer computer1 = findComputer(split[0], computers);
        Computer computer2 = findComputer(split[1], computers);
        computer1.addComputerConnection(computer2);
        computer2.addComputerConnection(computer1);
    }

    private static Computer findComputer(String name, ArrayList<Computer> computers) {
        for (Computer computer : computers) {
            if (computer.getName().equals(name)) {
                return computer;
            }
        }
        Computer newComputer = new Computer(name);
        computers.add(newComputer);
        return newComputer;
    }

    private static String findBiggestInterconnectedCluster(ArrayList<Computer> computers) {
        ArrayList<Computer> biggestCluster = new ArrayList<>();
        for (Computer computer : computers) {
            ArrayList<Computer> cluster = computer.calculateBiggestCluster(new ArrayList<>(), new ArrayList<>());
            cluster.add(computer);
            if (cluster.size() > biggestCluster.size()) {
                biggestCluster.clear();
                biggestCluster.addAll(cluster);
            }
        }
        biggestCluster.sort((a, b) -> a.getName().compareTo(b.getName()));
        String newString = "";
        for (Computer computer : biggestCluster) {
            newString += computer.getName() + ",";
        }

        return newString.substring(0, newString.length() - 1);
    }
}