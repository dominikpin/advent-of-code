package year2024.day17;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        int[] registers = new int[3];
        Scanner myReader = new Scanner(inputPath);
        for (int i = 0; i < 3; i++) {
            registers[i] = Integer.parseInt(myReader.nextLine().split(" ")[2]);
        }
        myReader.nextLine();
        System.out.println(simulateProgram(registers, myReader.nextLine().split(",")));
        myReader.close();
    }

    private static String simulateProgram(int[] registers, String[] instructionsString) {
        int[] instructions = new int[instructionsString.length];
        instructionsString[0] = instructionsString[0].charAt(instructionsString[0].length() - 1) + "";
        for (int i = 0; i < instructions.length; i++) {
            instructions[i] = Integer.parseInt(instructionsString[i]);
        }
        StringBuilder output = new StringBuilder();
        int instructionPointer = 0;
        while (instructionPointer < instructions.length) {
            int opcode = instructions[instructionPointer];
            int operandLiteral = instructions[instructionPointer + 1];
            int comboOperand = operandLiteral < 4 ? operandLiteral : registers[operandLiteral - 4];
            if (opcode == 0 || opcode == 6 || opcode == 7) {
                registers[opcode == 0 ? 0 : opcode - 5] = registers[0] >> comboOperand;
            } else if (opcode == 1 || opcode == 4) {
                registers[1] ^= opcode == 1 ? operandLiteral : registers[2];
            } else if (opcode == 2) {
                registers[1] = comboOperand % 8;
            } else if (opcode == 3 && registers[0] != 0) {
                instructionPointer = operandLiteral;
                continue;
            } else if (opcode == 5) {
                output.append((!output.isEmpty() ? "," : "") + comboOperand % 8);
            }
            instructionPointer += 2;
        }
        return output.toString();
    }
}