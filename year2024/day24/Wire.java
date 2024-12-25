package year2024.day24;

public class Wire {

    private String name;
    private String gate;
    private Wire[] inputs = new Wire[2];
    private boolean output;
    private boolean outputCalculated = false;

    public Wire(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public boolean getOutput() {
        if (this.outputCalculated || gate == null) {

        } else if (gate.equals("AND")) {
            this.output = this.inputs[0].getOutput() && this.inputs[1].getOutput();
        } else if (gate.equals("OR")) {
            this.output = this.inputs[0].getOutput() || this.inputs[1].getOutput();
        } else if (gate.equals("XOR")) {
            this.output = this.inputs[0].getOutput() ^ this.inputs[1].getOutput();
        }
        this.outputCalculated = true;
        return this.output;
    }

    public void setOutput(int output) {
        this.output = output == 1;
        this.outputCalculated = true;
    }

    public void resetOutput() {
        this.outputCalculated = false;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getGate() {
        return this.gate;
    }

    public void setInput(Wire input, int count) {
        this.inputs[count] = input;
    }

    public Wire getInput(int count) {
        return this.inputs[count];
    }

    @Override
    public String toString() {
        return String.format("%s: %s %s %s", this.name, this.gate == null ? this.output : this.inputs[0].name,
                this.gate == null ? "first" : this.gate, this.gate == null ? "wire" : this.inputs[1].name);
    }
}
