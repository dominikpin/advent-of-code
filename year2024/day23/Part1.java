package year2024.day23;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        ArrayList<Computer> computers = new ArrayList<>();
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            parseInput(myReader.nextLine(), computers);
        }
        myReader.close();
        System.out.println(findAllThreeWayConnections(computers));
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

    private static int findAllThreeWayConnections(ArrayList<Computer> computers) {
        int counter = 0;
        for (Computer computer : computers) {
            counter += findThreeWayConnectionsForComputer(computer);
        }
        return counter / 3;
    }

    private static int findThreeWayConnectionsForComputer(Computer computer) {
        int counter = 0;
        ArrayList<Computer> connections = computer.getConnections();
        for (Computer computer2 : connections) {
            for (Computer computer3 : computer2.getConnections()) {
                if (connections.contains(computer3)
                        && (computer.getName().startsWith("t") || computer2.getName().startsWith("t")
                                || computer3.getName().startsWith("t"))) {
                    counter++;
                }
            }
        }
        return counter / 2;
    }
}