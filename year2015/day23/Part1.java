package year2015.day23;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part1 {

    public Part1() throws FileNotFoundException {
        File inputPath = new File(System.getProperty("user.dir"), "input.txt");
        ArrayList<String> instructions = new ArrayList<>();
        Scanner myReader = new Scanner(inputPath);
        while (myReader.hasNextLine()) {
            instructions.add(myReader.nextLine());
        }
        myReader.close();
        System.out.println(executeInstructionsAndReturnB(instructions));
    }

    private static int executeInstructionsAndReturnB(ArrayList<String> instructions) {
        int[] registers = { 0, 0 };
        int instructionPointer = 0;
        while (instructionPointer < instructions.size()) {
            String instruction = instructions.get(instructionPointer);
            String[] split = instruction.split(" ");
            int register = split[1].charAt(0) == 'a' ? 0 : 1;
            if (split[0].equals("hlf")) {
                registers[register] /= 2;
            } else if (split[0].equals("tpl")) {
                registers[register] *= 3;
            } else if (split[0].equals("inc")) {
                registers[register]++;
            } else if (split[0].equals("jmp")) {
                instructionPointer += Integer.parseInt(split[1]);
                continue;
            } else if (split[0].equals("jie")) {
                if (registers[register] % 2 == 0) {
                    instructionPointer += Integer.parseInt(split[2]);
                    continue;
                }
            } else if (split[0].equals("jio")) {
                if (registers[register] == 1) {
                    instructionPointer += Integer.parseInt(split[2]);
                    continue;
                }
            }
            instructionPointer++;
        }
        return registers[1];
    }
}