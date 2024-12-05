package year2015.day07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part2 {
    public Part2() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        ArrayList<Wire> wires = new ArrayList<>();
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            parseInputAndMakeWires(myReader.nextLine(), wires);
        }
        int a = getWire(wires, "a").getValue();
        for (Wire wire : wires) {
            if (wire.getID().equals("b")) {
                wire.setValue(a);
                continue;
            } else if (wire.getSignal().isEmpty()) {
                continue;
            }
            wire.setValue(-1);
        }
        System.out.println(getWire(wires, "a"));
        myReader.close();
    }

    private static void parseInputAndMakeWires(String line, ArrayList<Wire> wires) {
        String[] split = line.split(" ");
        if (split.length == 3) {
            String ID1 = split[2];
            Wire wire1 = getWire(wires, ID1);
            if (Character.isDigit(split[0].charAt(0))) {
                int signal = Integer.parseInt(split[0]);
                wire1.setValue(signal);
            } else {
                String ID2 = split[0];
                Wire wire2 = getWire(wires, ID2);
                wire1.addSignal(wire2);
            }
        } else if (split.length == 4) {
            String ID1 = split[1];
            String ID2 = split[3];
            Wire wire1 = getWire(wires, ID1);
            Wire wire2 = getWire(wires, ID2);
            wire2.addSignal(wire1);
            wire2.setModule("NOT");
        } else if (split.length == 5) {
            if (split[1].equals("LSHIFT") || split[1].equals("RSHIFT")) {
                String ID1 = split[0];
                String ID2 = split[4];
                Wire wire1 = getWire(wires, ID1);
                if (Character.isDigit(ID1.charAt(0))) {
                    wire1.setValue(Integer.parseInt(ID1));
                }
                Wire wire2 = getWire(wires, ID2);
                wire2.addSignal(wire1);
                wire2.setModule(split[1] + " " + split[2]);
            } else {
                String ID1 = split[0];
                String ID2 = split[2];
                String ID3 = split[4];
                Wire wire1 = getWire(wires, ID1);
                if (Character.isDigit(ID1.charAt(0))) {
                    wire1.setValue(Integer.parseInt(ID1));
                }
                Wire wire2 = getWire(wires, ID2);
                if (Character.isDigit(ID2.charAt(0))) {
                    wire2.setValue(Integer.parseInt(ID2));
                }
                Wire wire3 = getWire(wires, ID3);
                wire3.addSignal(wire1);
                wire3.addSignal(wire2);
                wire3.setModule(split[1]);
            }
        }
    }

    private static Wire getWire(ArrayList<Wire> wires, String id) {
        for (Wire wire : wires) {
            if (wire.getID().equals(id)) {
                return wire;
            }
        }
        Wire newWire = new Wire(id);
        wires.add(newWire);
        return newWire;
    }
}
