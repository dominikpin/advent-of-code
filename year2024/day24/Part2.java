package year2024.day24;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        ArrayList<Wire> wires = new ArrayList<>();
        boolean option = true;
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            if (line.isEmpty()) {
                option = false;
                continue;
            }
            createWire(line, wires, option);
        }
        myReader.close();
        System.out.println(calculateWrongValues(wires));
    }

    private static void createWire(String line, ArrayList<Wire> wires, boolean option) {
        if (option) {
            String[] split = line.split(": ");
            Wire newWire = getWire(split[0], wires);
            newWire.setOutput(Integer.parseInt(split[1]));
        } else {
            String[] split = line.split(" ");
            Wire newWire1 = getWire(split[0], wires);
            Wire newWire2 = getWire(split[2], wires);
            Wire newWire3 = getWire(split[4], wires);
            newWire3.setInput(newWire1, 0);
            newWire3.setInput(newWire2, 1);
            newWire3.setGate(split[1]);
        }
    }

    private static Wire getWire(String wireName, ArrayList<Wire> wires) {
        for (Wire wire : wires) {
            if (wire.getName().equals(wireName)) {
                return wire;
            }
        }
        Wire newWire = new Wire(wireName);
        wires.add(newWire);
        return newWire;
    }

    private static String calculateWrongValues(ArrayList<Wire> wires) {
        ArrayList<Wire> remove = new ArrayList<>();
        for (Wire wire : wires) {
            if (wire.getName().startsWith("x") || wire.getName().startsWith("y")) {
                remove.add(wire);
            }
        }
        wires.removeAll(remove);

        ArrayList<Wire> wrongWires = getWrongWires(wires);
        wrongWires.sort((a, b) -> a.getName().compareTo(b.getName()));
        String output = "";
        for (Wire wire : wrongWires) {
            output += wire.getName() + ",";
        }
        return output.substring(0, output.length() - 1);
    }

    private static ArrayList<Wire> getWrongWires(ArrayList<Wire> wires) {
        ArrayList<Wire> problemWires = new ArrayList<>();
        ArrayList<Wire> startXORgate = new ArrayList<>();
        ArrayList<Wire> ANDgate = new ArrayList<>();
        for (Wire wire : wires) {
            if (wire.getName().startsWith("z")) {
                if (!wire.getName().equals("z45") && !wire.getGate().equals("XOR")) {
                    problemWires.add(wire);
                }
            } else if (wire.getGate().equals("XOR")) {
                boolean inputXOrY = wire.getInput(0).getName().startsWith("x")
                        || wire.getInput(0).getName().startsWith("y");
                if (!inputXOrY) {
                    problemWires.add(wire);
                } else {
                    startXORgate.add(wire);
                }
                continue;
            } else if (wire.getGate().equals("AND") && !wire.getInput(0).getName().substring(1, 3).equals("00")) {
                ANDgate.add(wire);
            }
        }
        for (Wire wire : wires) {
            if (wire.getGate().equals("XOR")) {
                startXORgate.removeIf(n -> n == wire.getInput(0) || n == wire.getInput(1));
            }
            if (wire.getGate().equals("OR")) {
                ANDgate.removeIf(n -> n == wire.getInput(0) || n == wire.getInput(1));
            }
        }
        problemWires.addAll(startXORgate);
        problemWires.addAll(ANDgate);
        return problemWires;
    }
}