package year2024.day17;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Part2 {

    public Part2() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        long[] registers = new long[3];
        Scanner myReader = new Scanner(inputPath);
        for (int i = 0; i < 3; i++) {
            registers[i] = Long.parseLong(myReader.nextLine().split(" ")[2]);
        }
        myReader.nextLine();
        System.out.println(simulateProgram(registers, myReader.nextLine().split(",")));
        myReader.close();
    }

    private static long simulateProgram(long[] registers, String[] instructionsString) {
        int[] instructions = new int[instructionsString.length];
        instructionsString[0] = instructionsString[0].charAt(instructionsString[0].length() - 1) + "";
        for (int i = 0; i < instructions.length; i++) {
            instructions[i] = Integer.parseInt(instructionsString[i]);
        }
        ArrayList<Long> seen = new ArrayList<>();
        int depth = 0;
        Queue<Long> queue = new LinkedList<>();
        queue.offer(0L);
        Queue<Long> newQueue = new LinkedList<>();
        long bRegister = registers[1];
        long cRegister = registers[2];
        while (!queue.isEmpty()) {
            long startA = queue.poll() * 8;
            for (int i = 0; i < 8; i++) {
                registers[0] = startA + i;
                registers[1] = bRegister;
                registers[2] = cRegister;
                int instructionPointer = 0;
                ArrayList<Long> output = new ArrayList<>();
                while (instructionPointer < instructions.length) {
                    int opcode = instructions[instructionPointer];
                    int operandLiteral = instructions[instructionPointer + 1];
                    long comboOperand = operandLiteral < 4 ? operandLiteral : registers[operandLiteral - 4];
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
                        if (output.size() > instructions.length) {
                            break;
                        }
                        output.add(comboOperand % 8);
                    }
                    instructionPointer += 2;
                }
                if (output.size() >= depth + 1
                        && instructions[instructions.length - 1 - depth] == output.get(output.size() - 1 - depth)) {
                    if (instructions.length - 1 - depth == 0) {
                        return startA + i;
                    }
                    if (!seen.contains(startA + i)) {
                        seen.add(startA + i);
                        newQueue.add(startA + i);
                    }
                }
            }
            if (queue.isEmpty()) {
                queue.addAll(newQueue);
                newQueue.clear();
                depth++;
            }
        }
        return -1;
    }
}