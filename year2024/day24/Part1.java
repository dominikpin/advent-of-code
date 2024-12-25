package year2024.day24;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
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
        System.out.println(getOutput(wires));
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

    private static long getOutput(ArrayList<Wire> wires) {
        long output = 0;
        for (Wire wire : wires) {
            if (wire.getName().charAt(0) == 'z' && wire.getOutput()) {
                output += 1L << Integer.parseInt(wire.getName().substring(1, wire.getName().length()));
            }
        }
        return output;
    }
}