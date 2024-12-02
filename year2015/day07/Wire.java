package year2015.day07;

import java.util.ArrayList;

public class Wire {

    private String ID;
    private int value = -1;
    private ArrayList<Wire> connectedWires = new ArrayList<>();
    private String module;

    public Wire(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public int getValue() {
        if (value == -1) {
            if (module == null) {
                this.value = connectedWires.get(0).getValue();
            } else if (module.equals("NOT")) {
                this.value = ~connectedWires.get(0).getValue();
            } else if (module.equals("AND")) {
                this.value = connectedWires.get(0).getValue() & connectedWires.get(1).getValue();
            } else if (module.equals("OR")) {
                this.value = connectedWires.get(0).getValue() | connectedWires.get(1).getValue();
            } else {
                String[] moduleSplit = module.split(" ");
                int bitShift = Integer.parseInt(moduleSplit[1]);
                if (moduleSplit[0].equals("LSHIFT")) {
                    this.value = connectedWires.get(0).getValue() << bitShift;
                } else {
                    this.value = connectedWires.get(0).getValue() >> bitShift;
                }
            }

        }
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ArrayList<Wire> getSignal() {
        return connectedWires;
    }

    public void addSignal(Wire wire) {
        this.connectedWires.add(wire);
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String toString() {
        if (value == -1) {
            getValue();
        }
        return String.format("%s: %d", this.ID, this.value < 0 ? this.value + (int) Math.pow(2, 16) : this.value);
    }
}
